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
package propra;

/**
 * This interface defines that all objects of type GeneratorState must have a 
 * method called getDescription() which returns a String describing the
 * GeneratorState.
 * 
 * This interface also defines constants inside an enum called common that each
 * object of type GeneratorState (or a subtype thereof) can have. Specific
 * constants for specific subtypes of GeneratorState should be declared in a
 * separate enum that implements this interface (see for example 
 * SimpleGeneratorState.java).
 * 
 * A good example how to use an object of type GeneratorState is inside the
 * generate() method of the SimpleGeneratorModel.
 *
 * @author Christoph Baumhardt
 */
public interface GeneratorState {
           
    /**
     * Describes current GeneratorState (for status bar).
     * 
     * @return  A String that describes the current state
     */
    public String getDescription();
    
    /**
     * The following enum defines constants that each object of type 
     * GeneratorState (or any subtype thereof) can have, meaning GeneratorStates
     * that are common to all Generators can have.
     */
    public enum Common implements GeneratorState {

        READY("Ready!"),
        FINISHED_READY("Finished! And ready again!");

        private final String description;

        Common(String description) {
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
        
    }

}
