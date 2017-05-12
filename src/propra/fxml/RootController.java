/*
 * The MIT License
 *
 * Copyright 2017 Christoph Baumhardt.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package propra.fxml;

import propra.model.GeneratorState;
import propra.MainApp;
import propra.SaveImageCallable;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

/**
 * FXML Controller class for RootView.fxml
 *
 * @author Christoph Baumhardt
 */
public class RootController implements SaveImageCallable {
    
    @FXML
    private MenuItem menuItemSaveImage;     
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label statusLabel;   
    
    
    private Stage generatorStage;
    private GeneratorController generatorController;
    private Canvas canvas;

    
    @FXML
    public void initialize() {
        statusLabel.textProperty().setValue("No generator selected.");
        menuItemSaveImage.setDisable(true); // cannot save if nothing generated
    }
    
    @FXML
    private void handleSaveImage(){
        saveImage("generated_image.png");
    }
    
    /**
     * Creates a dialog to save an image as png.
     * 
     * @param fileName  The initial filename displayed in the save image dialog
     */
    @Override
    public void saveImage(String fileName){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.setInitialFileName(fileName);
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        );  
        FileChooser.ExtensionFilter extFilter 
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        // show Save Image dialog and process user input
        File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());
        if (file != null) {
            try {
                WritableImage writableImage = canvas.snapshot(null, null);
                RenderedImage renderedImage = 
                        SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
                
                statusLabel.textProperty().setValue("Saved!");
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }            
        } // else file save was cancelled by user
    }
    
    @FXML
    private void showSimpleGeneratorView() {
        showSpecializedGeneratorView("Simple Generator",
                "fxml/SimpleGeneratorView.fxml");
    }    

    @FXML
    private void showConwayGeneratorView() {
        showSpecializedGeneratorView("Conway Generator",
                "fxml/ConwayGeneratorView.fxml");
    } 
    
    /**
     * Displays a new view of a specialized Generator.
     * 
     * @param generatorName  The name associated with the generator
     * @param pathToFXMLFile The path to the fxml file of the wanted view
     */    
    private void showSpecializedGeneratorView(String generatorName,
            String pathToFXMLFile){
        if (generatorController != null && 
                generatorController.getModel().getGeneratorName().
                        equals(generatorName)){
            // window for specialized Generator exists already -> no creation
            generatorStage.requestFocus();
        } else {
            try {
                if (generatorController != null) {
                    // a window for a different specialized Generator exists
                    // already -> close it first
                    generatorStage.close();
                }
                // create new view
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource(pathToFXMLFile));
                Parent content = loader.load();
                
                // let the root view listen to the GeneratorState of the newly
                // created model (to update statusbar and display generated 
                // image when it is finished)
                generatorController = loader.getController();
                generatorController.getModel().generatorStateProperty().
                        addListener(new ChangeListener<GeneratorState>(){
                    @Override
                    public void changed(ObservableValue<? extends 
                            GeneratorState> observable, GeneratorState oldValue,
                            GeneratorState newValue) {
                        // Make sure the following runs always inside JavaFX
                        // Application Thread (even if started from another
                        // Thread), as UI changes need to be done in there
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() { 
                                statusLabel.textProperty().setValue(
                                        newValue.getDescription());
                                
                                if (newValue == 
                                        GeneratorState.FINISHED_READY) {
                                    canvas = generatorController.getModel().
                                            getCanvas();
                                    scrollPane.setContent(canvas);
                                    menuItemSaveImage.setDisable(false);
                                }                                
                                
                            }
                        });                   
                    }

                });
                
                statusLabel.textProperty().setValue(
                        generatorController.getModel().getStateDescription());
                generatorStage = new Stage();
                generatorStage.setTitle(
                        generatorController.getModel().getGeneratorName());
                generatorStage.setOnCloseRequest((WindowEvent e) -> {
                    generatorController.getModel().stopBackgroundThread();
                    generatorController = null;
                    statusLabel.textProperty().setValue(
                            "No generator selected.");
                });                 
                generatorStage.setScene(new Scene(content));
                generatorStage.setResizable(false);
                generatorStage.show();                
                
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void handleExit() {
        Platform.exit(); // close all windows of application gracefully
    }
    
    
}
