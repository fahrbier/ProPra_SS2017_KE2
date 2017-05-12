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
package propra.model.conway;

/**
 *
 * @author holger
 */
public class Board {
    private final boolean[][] grid;
    private final int width;
    private final int height;
    private final double initialAliveness;

    /**
     *
     * @return
     */
    public int getWidth() {
        return this.width;
    }
    
    /**
     *
     * @return
     */
    public int getHeight() {
        return this.height;
    }    
    
    public double getInitialAliveness() {
        return this.initialAliveness;
    }
    
    /**
     *
     * @return
     */
    public boolean[][] getGrid() {
        return grid;
    }
    
    public void setCell(int x, int y, boolean alive) {
        this.grid[x][y] = alive;
    }
    
    
    /**
     * initialize an empty Board (need that to set the next generation up)
     * @param width Width of the board
     * @param height Height of the board
     */
    public Board(int width, int height) {
            
        this.width = width;
        this.height = height;
        this.initialAliveness = 0;
        
        grid = new boolean[width][height];
    
    }
    
    
    /**
     * create a new board with some living cells
     * @param width Width of the board
     * @param height Height of the board
     * @param initialAliveness value between 0 and 1 to set the probability that a cell is initialized alive
     */
    public Board(int width, int height, double initialAliveness) {
        
        this.width = width;
        this.height = height;
        this.initialAliveness = initialAliveness;
        
        
        grid = new boolean[width][height];
        
        for (int x=0; x < width; x++) {
            for (int y=0; y < height; y++) {
                grid[x][y] = Math.random() <= initialAliveness;
            }
        }
    }
    

    public Board getNextGeneration() {
        Board nextBoard = new Board(this.width, this.height);
        
        for (int x=0; x < this.width; x++) {
            for (int y=0; y < this.height; y++) {
                switch( this.getAmountMooreNeighbors(x, y) ) {
                    case 2:
                        if (this.isAlive(x, y)) {
                            nextBoard.setCell(x, y, true);  
                        }
                    break;
                    case 3:
                        nextBoard.setCell(x, y, true);
                    break;
                    default:
                        nextBoard.setCell(x, y, false);
                }                
            }
        }   

        
        return nextBoard;
    }
    
    private boolean isAlive(int x, int y) {
        return this.grid[x][y];
    }
    
    private int getAmountMooreNeighbors(int x, int y) {
        int neighborsAliveCount = 0 ;
        for (int xn = x-1; xn <= x+1; xn++) {
            for (int yn = y-1; yn <= y+1; yn++) {
                if ( //-- check whether I am still on the board and the cell, other than myself, is alive
                    xn >=0 && xn < this.width &&
                    yn >=0 && yn < this.height &&
                    !(x == xn && y == yn) && //-- don't count myself
                    this.grid[xn][yn]
                   ){
                    neighborsAliveCount++;
                }
            }
        }
        return neighborsAliveCount;
    }
    
    
    /**
     *
     */
    public void toConsole() {
        
        for (int w=0; w < this.width; w++) {
            String line = new String();
            System.out.println();
            for (int h=0; h < this.height; h++) {
                if (grid[w][h]) {
                    line = line.concat("+");
                }
                else {
                    line = line.concat("o");
                }
            }
            System.out.println(line);
        }       
    }
    
}
