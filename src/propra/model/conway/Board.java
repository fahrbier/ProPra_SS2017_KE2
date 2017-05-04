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

    //-- initiales Board with only dead cells    
    public Board(int size) {
        grid = new boolean[size][size];
        
        for (int w=0; w<size; w++) {
            for (int h=0; h<size; h++) {
                //-- About the half of the cells should be alive on init
                if (Math.random()<=0.5){
                    grid[w][h] = true; 
                }
                else {
                    grid[w][h] = false;
                }
            }
        }
    }
    
    public boolean[][] getGrid() {
        return grid;
    }
    
    public void toConsole() {
        int size = grid.length;
        for (int w=0; w<size; w++) {
            String line = new String();
            System.out.println();
            for (int h=0; h<size; h++) {
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
