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
import propra.model.conway.Board;

/**
 *
 * @author holger
 */
public class ConwayGeneratorModel extends GeneratorModel {

    private int height;
    private int width;
    private int generations;
    
    private Board board;
    
    private final int cellSize = 10;

    public ConwayGeneratorModel() {
        height = 30;
        width = 50;
        generations = 50;
    }
    
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int value) {
        if (value >  0 && value <= 300) {
            width = value;
        } else {
            throw new IllegalArgumentException();
        }
    }    
    
    public int getHeight() {
        return height;
    }

    public void setHeight(int value) {
        if (value >  0 && value <= 300) {
            height = value;
        } else {
            throw new IllegalArgumentException();
        }
    }     

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int value) {
        if (value >  0 && value <= 100) {
            generations = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    
    
    @Override
    public String getGeneratorName() {
        return "Conway GoL";
    }

    @Override
    public void generate() {
        canvas = new Canvas(width * cellSize, height * cellSize);
        
        setGeneratorState("Creating initial Population");
        
        board = new Board(width,height,0.2);
        drawBoardToCanvas(board.getGrid() ,canvas.getGraphicsContext2D());

        setGeneratorState(GeneratorState.FINISHED_READY);
        
        for (int g=1; g <= generations; g++){
            try {
                Thread.sleep(500);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }        
            setGeneratorState("Generation " + g);
            board = board.getNextGeneration();        
            drawBoardToCanvas(board.getGrid() ,canvas.getGraphicsContext2D());
        }
        
    }
    

    
    private void drawBoardToCanvas(boolean[][] grid, GraphicsContext gc) {
        gc.clearRect(0, 0, width * cellSize, height * cellSize);
        for (int x=0; x < grid.length; x++) {
            for (int y=0; y < grid[x].length; y++) {
                if (grid[x][y]){
                    gc.setFill(Color.BLUE);        
                    gc.fillOval(x * cellSize, y * cellSize, cellSize, cellSize);
                }                
            }
        }  
    }


}
