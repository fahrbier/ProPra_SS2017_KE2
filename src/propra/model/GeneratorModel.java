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

import javafx.scene.canvas.Canvas;
import propra.SaveImageCallable;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 * This is an abstract GeneratorModel which all specialized
 * GeneratorModel (for example SimpleGeneratorModel) extend. It 
 * defines the things all subtypes of GeneratorModel have in common, for example
 * a canvas to draw on, a GeneratorState property, the ability to save an image
 * and a generate() method to actually generate the desired image onto the
 * canvas.
 *
 * @author Christoph Baumhardt
 */
public abstract class GeneratorModel implements SaveImageCallable {

    protected String generatorName; 
    protected Canvas canvas; // to draw on
    protected Thread backgroundThread; // to execute generate()
    
    // instead of a normal variable for GeneratorState use a property, as a
    // property can be easily monitored for changes
    // tutorial on properties: 
    //https://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm
    private final ObjectProperty<GeneratorState> generatorState;

    public GeneratorModel() {
        // name will be overwritten by specialized GeneratorModel
        //generatorName = "Abstract Generator";
        generatorName = getGeneratorName();
        generatorState = new SimpleObjectProperty<>(this, "generatorState",
                GeneratorState.READY);
    }
    
    abstract public String getGeneratorName();
    
    /*public String getName() {
        return generatorName;
    }*/
    
    /**
     * Returns the current canvas (can be null).
     *
     * @return The current canvas associated with the GeneratorModel
     */
    public Canvas getCanvas() {
        return canvas;
    }
    
    /**
     * Each GeneratorModel has to have a generate() method that affects the
     * canvas.
     *
     */    
    abstract void generate();
    

    /**
     * Define and use a new Thread for the potentially time-consuming generate()
     * method, so that the Java FX Application Thread does not get blocked
     * (which would result in unresponsiveness in the GUI).
     */
    public void generateInNewThread() {
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                generate();
                generatorStateProperty().setValue(
                        GeneratorState.FINISHED_READY);
                return null;
            }
        };
        backgroundThread = new Thread(task);
        backgroundThread.start();  
        
    }

    /**
     * Saves the current canvas directly under a filename.
     *
     * @param filename The filename under which the canvas should be saved
     */
    @Override
    public void saveImage(String filename){
        WritableImage image = canvas.snapshot(new SnapshotParameters(), null);
        File file = new File(filename);
        try {
            WritableImage writableImage = canvas.snapshot(null, null);
            RenderedImage renderedImage = 
                    SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }                 
    }

    /**
     * Describes the current state of the GeneratorModel in words.
     *
     * @return Description of the current GeneratorState
     */
    public final String getStateDescription() {
        return generatorState.get().getDescription();
    } 

    /**
     * Stops the thread the generate() method uses for its computation.
     *
     */    
    public void stopBackgroundThread(){
        if(backgroundThread != null && backgroundThread.isAlive()){
            backgroundThread.interrupt();
            // toggles only a status bit, which still needs to be checked in 
            // generate(): if(Thread.currentThread().isInterrupted()){return;}
        }
    }
    
    // declare the typical functions associated with properties
    
    public final GeneratorState getGeneratorState() {
        return generatorState.get();
    }
    
    public final void setGeneratorState(GeneratorState newGeneratorState) {
        generatorState.set(newGeneratorState);    
    }
    
    public final ObjectProperty<GeneratorState> generatorStateProperty() {
        return generatorState;
    }

    // easy setting of GeneratorState
    
    public final void setGeneratorState(String description) {
        generatorState.set(new GeneratorState(description));
    }
}
