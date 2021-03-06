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

import propra.model.GeneratorModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This is an abstract GeneratorController which all specialized
 * GeneratorController (for example SimpleGeneratorController) extend. It 
 * defines the things all subtypes of GeneratorController have in common:
 * a Generate-button, a method to handle that button and method to get the
 * underlying specific GeneratorModel that each GeneratorController has to have.
 *
 * @author Christoph Baumhardt
 */
public abstract class GeneratorController {
    
    /**
     * Each SpecializedGeneratorView.fxml file has to have a Button with fx:id
     * "buttonGenerate" and onAction "#handleGenerate".
     * 
     */     
    @FXML private Button buttonGenerate;

    /**
     * This automatically called method makes sure that when the generateButton
     * has the focus and Enter is pressed the same thing happens as if the user
     * clicked on the Button.
     * 
     */       
    public void initialize() {
        buttonGenerate.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.ENTER) {
                handleGenerate();
            }            
        });        
    }    
    
     /**
     * Returns the model each specialized GeneratorController declares. Note 
     * that the model is not defined in this abstract class as a GeneratorModel,
     * but rather in the subclasses of GeneratorController as a specialized
     * GeneratorModel so that the specialized GeneratorController can access
     * its individual specialized functions.
     * 
     * @return The GeneratorModel that is linked with the GeneratorController
     * 
     */    
    abstract GeneratorModel getModel();
    
    
    /**
     * Handles a press on the buttonGenerate by initiating the
     * generateInNewThread() method of the GeneratorModel, which creates an
     * a new Image.
     * 
     */     
    public void handleGenerate(){
        getModel().generateInNewThread();
    };
  
    /**
     * Can be used in a subclass of GeneratorController to show an Alert if 
     * there was an invalid user input.
     * 
     * @param description The description of the invalid input
     */     
    protected void showInputAlert(String description) {
        showAlert("Invalid Input", description);
    }

    /**
     * Can be used in a subclass of GeneratorController to show an Alert.
     * 
     * @param title The title of the alert
     * @param description The description of the alert
     */     
    protected void showAlert(String title, String description){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(description);
        alert.showAndWait();        
    }    
}
