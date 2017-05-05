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
    private boolean[][] grid;
    
    private int width;
    
    private int height;
    
    private double aliveness;

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
    
    public double getAliveness() {
        return this.aliveness;
    }
    
    /**
     *
     * @return
     */
    public boolean[][] getGrid() {
        return grid;
    }
    
    //-- initiales Board with only dead cells 

    /**
     *
     * @param width Width of the board
     * @param height Height of the board
     * @param aliveness value between 0 and 1 to set the probability that a cell is initialized alive
     */
    
    public Board(int width, int height, double aliveness) {
        
        this.width = width;
        this.height = height;
        this.aliveness = aliveness;
        
        
        grid = new boolean[this.getWidth()][this.getHeight()];
        
        for (int w=0; w < this.getWidth(); w++) {
            for (int h=0; h < this.getHeight(); h++) {
                //-- About the half of the cells should be alive on init
                if (Math.random() <= this.getAliveness()){
                    grid[w][h] = true; 
                }
                else {
                    grid[w][h] = false;
                }
            }
        }
    }
    

    public Board getNextGeneration() {
        Board tmpBoard = new Board(this.getWidth(), this.getHeight(), this.getAliveness());
        
        for (int w=0; w < this.getWidth(); w++) {
            for (int h=0; h < this.getHeight(); h++) {
                System.out.println("n:" + this.getAmountMooreNeighbors(w, h));
                //switch( this.getAmountMooreNeighbors(w, h) ) {
                
                //}
                
                //if (grid[w][h]) {
         }
        }   
        
        
        
        return tmpBoard;
    }
    
    private int getAmountMooreNeighbors(int x, int y) {
        int neighborsAliveCount = 0 ;
        for (int xn = x-1; xn <= x+1; xn++) {
            for (int yn = y-1; yn <= x+1; yn++) {
                System.out.print("[" + xn + ":" + yn + "]");
                if ( //-- check whether I am still on the board and the cell is alive
                    xn >=0 && xn < this.getWidth() &&
                    yn >=0 && yn < this.getHeight() &&
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
        
        for (int w=0; w < this.getWidth(); w++) {
            String line = new String();
            System.out.println();
            for (int h=0; h < this.getHeight(); h++) {
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
