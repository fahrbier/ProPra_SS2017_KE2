/*
 * The MIT License
 *
 * Copyright 2017 holger.
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

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import propra.model.GeneratorModel;
import propra.model.SierpinskiGeneratorModel;

/**
 * FXML Controller class
 *
 * @author holger
 */
public class SierpinskiGeneratorController extends GeneratorController {

    @FXML private TextField textFieldWidth;  
    @FXML private TextField textFieldGenerations;    
    
    SierpinskiGeneratorModel model;   

    @Override
    GeneratorModel getModel() {
        return model;
    }

        


    
    @Override
    public void initialize() {
        super.initialize();
        model = new SierpinskiGeneratorModel();
        
        // display values from model
        textFieldWidth.textProperty().setValue( String.valueOf(model.getWidth()) );
        // process changes from the UI
        textFieldWidth.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    String s = textFieldWidth.textProperty().getValue();
                    int w = Integer.parseInt(s);
                    model.setWidth(w);
                } catch (IllegalArgumentException ex) {
                    // catches both the possible NumberFormatException from
                    // parseInt() as well as the possible IllegalArgumentExcept.
                    // from SimpleGeneratorModel.setWidth(..)
                    
                    // display last valid value for width from model
                    textFieldWidth.textProperty().setValue(
                            String.valueOf(model.getWidth()));
                    showInputAlert("Width requires an integer value between 1" +
                            " and 300.");
                }
            }
        });      

        // display values from model
        textFieldGenerations.textProperty().setValue( String.valueOf(model.getGenerations()) );
        // process changes from the UI
        textFieldGenerations.focusedProperty().addListener((observableBoolean, oldValue, newValue) -> {
            if (!newValue){ // newValue=0 means no focus -> if no longer focused
                try {
                    String s = textFieldGenerations.textProperty().getValue();
                    int w = Integer.parseInt(s);
                    model.setGenerations(w);
                } catch (IllegalArgumentException ex) {
                    // catches both the possible NumberFormatException from
                    // parseInt() as well as the possible IllegalArgumentExcept.
                    // from SimpleGeneratorModel.setWidth(..)
                    
                    // display last valid value for width from model
                    textFieldGenerations.textProperty().setValue(
                            String.valueOf(model.getGenerations()));
                    showInputAlert("Generations requires an integer value between 1" +
                            " and 100.");
                }
            }
        });        



        
    }
}