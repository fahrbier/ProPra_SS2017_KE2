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
package propra.model;

import propra.SimpleGeneratorState;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

/**
 *
 * @author Christoph Baumhardt
 */
public class SimpleGeneratorModel extends GeneratorModel {
    
    // restrictions on the input parameters, can be changed
    private static final int MIN_WIDTH = 1;
    private static final int MAX_WIDTH = 3000;
    private static final int MIN_HEIGHT = 1;
    private static final int MAX_HEIGHT = 3000;
    
    // instead of two int use properties, as they can be easily monitored
    // for changes
    // tutorial on properties:
    // https://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm
    private final IntegerProperty width = new SimpleIntegerProperty(600); 
    private final IntegerProperty height = new SimpleIntegerProperty(400);
    
    public SimpleGeneratorModel() {
        name = "Simple Generator";
        setGeneratorState(SimpleGeneratorState.Common.READY);
    }
    
    // declare the typical functions associated with properties
    
    public final int getWidth() {
        return width.get();
    }
    
    public final int getHeight() {
        return height.get();
    }

    public final void setWidth(int value) {
        width.set(value);
    }
    
    public final void setHeight(int value) {
        height.set(value);
    }
    
    public final IntegerProperty widthProperty() {
        return width;
    }
    
    public final IntegerProperty heightProperty() {
        return height;
    }

    // functions for restrictions check
    
    static private boolean isValidWidth(String s) {
        try {
            int value = Integer.parseInt(s);
            return MIN_WIDTH <= value && value <= MAX_WIDTH;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    static private boolean isValidHeight(String s) {
        try {
            int value = Integer.parseInt(s);
            return MIN_HEIGHT <= value && value <= MAX_HEIGHT;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
    
    // functions to set the properties not only for integer inputs, but
    // for the needed String inputs
    
    public void setWidth(String s) {
        if (isValidWidth(s)) {
            width.set(Integer.parseInt(s));
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Width requires an integer value between " + MIN_WIDTH + " and " + MAX_WIDTH + ".");
            alert.showAndWait();
        }
    }
    
    public void setHeight(String s) {
        if (isValidHeight(s)) {
            height.set(Integer.parseInt(s));
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Height requires an integer value between " + MIN_HEIGHT + " and " + MAX_HEIGHT + ".");
            alert.showAndWait();
        }
    }
 
    @Override
    public void generate() {     
                
        // make sure to always use values from the same time:
        final int w = getWidth();
        final int h = getHeight();

        setGeneratorState(SimpleGeneratorState.CREATING_CANVAS);
        canvas = new Canvas(w, h);

        setGeneratorState(SimpleGeneratorState.FILLING_BACKGROUND);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, w, h);

        setGeneratorState(SimpleGeneratorState.DRAWING_BLUE_CIRCLE);
        double diameter = Math.min(w, h);
        gc.setFill(Color.BLUE);
        // draw a circle in the middle of the canvas
        gc.fillOval((w-diameter)/2., (h-diameter)/2.,
                diameter, diameter);

        setGeneratorState(SimpleGeneratorState.Common.FINISHED_READY);
        
        // NOTE: To show the different middle states (they are usually too fast
        // for the human eye) put the following code snippet before each call
        // of setGeneratorState(...) inside this method. This simulates a
        // time-consuming generate process.
        /*try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }*/   
     
    }
    
}
