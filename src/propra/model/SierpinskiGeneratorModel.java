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
package propra.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import propra.model.sierpinski.Row;

/**
 *
 * @author holger
 */
public class SierpinskiGeneratorModel extends GeneratorModel {

    private int width;
    private int generations;
    
    
    private Row row;
    
    private final int cellSize = 5;
    
    @Override
    public String getGeneratorName() {
        return "Sierpinski Triangle";
    }

    @Override
    void generate() {

        
        
        System.out.println ("generate sierpinski");
        
        this.width = 100;
        this.generations = 50;
         
        int height = this.generations;
        
        canvas = new Canvas(width * cellSize, height * cellSize);
        
        row = new Row(this.width, false);
        drawRowToCanvas(row.getRow() , 0, canvas.getGraphicsContext2D());
         
        setGeneratorState(GeneratorState.FINISHED_READY);
         
        for (int g=1; g <= this.generations; g++){
           try {
               Thread.sleep(500);
           } catch(InterruptedException ex) {
               Thread.currentThread().interrupt();
           }        
           setGeneratorState("Generation " + g);
           row = row.getNextGeneration();        
            
           drawRowToCanvas(row.getRow() , g, canvas.getGraphicsContext2D());
        }
    }

    private void drawRowToCanvas(boolean[] row, int gen, GraphicsContext gc) {
 
        for (int x=0; x < row.length; x++) {
            if (row[x]){
                gc.setFill(Color.BLUE);        
                gc.fillOval(x * cellSize, gen * cellSize, cellSize, cellSize);
            }
        }
        
    }

    
}
