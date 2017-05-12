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

/**
 * An object of this class describes the state of a GeneratorModel.
 * 
 * There a two fixed states that every GeneratorModel can have which are hence
 * defined as constants in here. They are set automatically by the program and
 * are e.g. used to automatically determine when to display a generated canvas.
 *
 * @author Christoph Baumhardt
 */
public class GeneratorState {
    
    public static final GeneratorState READY =
            new GeneratorState("Ready!");
    public static final GeneratorState FINISHED_READY =
            new GeneratorState("Finished! And ready again!");
    
    private final String description;
           
    /**
     * Describes current GeneratorState (for status bar).
     * 
     * @return  A String that describes the current state
     */
    public String getDescription() {
        return description;
    }
    
    
    public GeneratorState(String description) {
        this.description = description;
    }

}