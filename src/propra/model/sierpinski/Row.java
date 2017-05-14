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
package propra.model.sierpinski;

/**
 *
 * @author holger
 */
public class Row {
    
    private final int width;
    private final boolean[] row;

    public boolean[] getRow() {
        return row;
    }
    
    public void setCell(int x, boolean alive) {
        this.row[x] = alive;
    }    
    
    public Row (int width, boolean emptyRow) {
        this.width = width;          
        row = new boolean[width];
        
        if (!emptyRow) { 
            //- set the one living cell at the start in the middle
            row[row.length / 2] = true;
        }    
    }
    
    
    public Row getNextGeneration() {
        Row nextRow = new Row(this.width, true);
        
        for (int x=0; x < this.width; x++) {
            /* 
              using a switch case to be able to extend to different rules
              right now only
              000->0, 001->1, 010->1, 011->1, 100->1, 101->1, 110->1, 111->0 
              is wired in here.
              And I am simply looking at me an my 2 neigbors and count and not 
              the binary representation like the rule shows here. Need to get
              it work before I put more fancy to it.
            */
            switch ( this.getAmountMeAndMyNeigbors(x) ){
                case 1:
                case 2:
                    nextRow.setCell(x,true);
                break;
                default:
                    nextRow.setCell(x,false);
            }
        }
        return nextRow;
    }
    
    private int getAmountMeAndMyNeigbors(int x) {
       int neighborsAliveCount = 0; 
       for (int xn = x-1; xn <= x+1; xn++) {
           if ( //-- check whether I am still in the row's boundaries and the cell is alive
               xn >=0 && xn < this.width &&
                    this.row[xn]
                   ){
                    neighborsAliveCount++;
                }
       }
       return neighborsAliveCount; 
    }

    public void toConsole() {
        String line = new String();
        System.out.println();
        for (int w=0; w < this.width; w++) {
                if (row[w]) {
                    line = line.concat("+");
                }
                else {
                    line = line.concat("o");
                }
        } 
        System.out.println(line);
    }
}
