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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import propra.model.ConwayGeneratorModel;
import propra.model.GeneratorModel;
import propra.model.SimpleGeneratorModel;

/**
 * FXML Controller class
 *
 * @author holger
 */
public class ConwayGeneratorController extends GeneratorController {  

    ConwayGeneratorModel model;

    
    @Override
    public ConwayGeneratorModel getModel() {
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
        
        model = new ConwayGeneratorModel();
        
    }    
    
}
