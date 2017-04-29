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
import propra.model.SimpleGeneratorModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Christoph Baumhardt
 */
public class SimpleGeneratorController extends GeneratorController {

    @FXML private TextField textFieldWidth;  
    @FXML private TextField textFieldHeight;
    
    SimpleGeneratorModel model;

    
    @Override
    public GeneratorModel getModel() {
        return model;
    }
    
    /**
     * This automatically called method creates a new SimpleGeneratorModel and 
     * links it with its view correctly, so that changes on one of them gets
     * reflected in the other.
     * 
     */      
    @Override
    public void initialize() {
        super.initialize(); // activate buttonGenerate on Enter
        
        model = new SimpleGeneratorModel();
        
        // display values from model
        textFieldWidth.textProperty().setValue(
                String.valueOf(model.getWidth()));
        textFieldHeight.textProperty().setValue(
                String.valueOf(model.getHeight()));
        
        // change model if user changes something on the view
        textFieldWidth.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldValue, Boolean newValue) {
                // newValue = 0  means no focus
                if (!newValue) { // when textfield loses focus
                    model.setWidth(textFieldWidth.textProperty().getValue());
                    // display current value from model (for bad user entry)
                    textFieldWidth.textProperty().set(
                            String.valueOf(model.getWidth()));
                }
            }
        });
        textFieldHeight.focusedProperty().addListener(
                new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldValue, Boolean newValue) {
                // newValue = 0  means no focus
                if (!newValue) { // when textfield loses focus
                    model.setHeight(textFieldHeight.textProperty().getValue());
                    // display current value from model (for bad user entry)
                    textFieldHeight.textProperty().set(
                            String.valueOf(model.getHeight()));
                }
            }
        });
        
        // change view if something changes on the model
        // this may be good for the future, for example if one input parameter
        // sets an upper limit for another input parameter
        model.widthProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldValue,
                    Object newValue) {
                textFieldWidth.textProperty().setValue(
                        String.valueOf(model.getWidth()));
            }            
        });
        // shows a slightly different way than above to do the same
        model.heightProperty().addListener(new ChangeListener<Number>() { 
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number oldValue, Number newValue) {
                textFieldHeight.textProperty().setValue(
                        String.valueOf(newValue));
            }            
        });

    }        


}
