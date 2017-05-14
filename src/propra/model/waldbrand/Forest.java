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
package propra.model.waldbrand;

/**
 *
 * @author holger
 */
public class Forest {
    
    public static final int BURNED_TREE = 0; 
    public static final int TREE = 1;   
    public static final int FIRE = 2;
    
    private final int[][] grid;
    private final int eastWest;
    private final int northSouth;
    
    private double probabilityTreeCatchesFire = 0.000005; // gamma
    private double probabilityNewTreeGrows    = 0.01;    // alpha

    /**
     *
     * @return
     */
    public int getEastWest() {
        return this.eastWest;
    }
    
    /**
     *
     * @return
     */
    public int getNorthSouth() {
        return this.northSouth;
    }    
    
    
    /**
     *
     * @return
     */
    public int[][] getGrid() {
        return grid;
    }
    
    public void setCell(int x, int y, int status) {
        if ( 
            status != Forest.BURNED_TREE && 
            status != Forest.TREE &&
            status != Forest.FIRE
           ) {
           return;
        } 
        this.grid[x][y] = status;
    }
    
    
    /**
     * initialize an empty Board (need that to set the next generation up)
     * @param ew size of forest in east-west spread
     * @param ns size of forest in north-south spread
     * @param alpha probability that a new tree grows
     * @param gamma probability that a lightning strikes and starts a fire
     */
    public Forest(int ew, int ns, double alpha, double gamma) {
            
        this.eastWest = ew;
        this.northSouth = ns;
        this.probabilityNewTreeGrows = alpha;
        this.probabilityTreeCatchesFire = gamma;
        
        grid = new int[ew][ns];
        
        for (int x=0; x < ew; x++) {
            for (int y=0; y < ns; y++) {
                

             //grid[x][y] = Math.random() <= initialAliveness;
            }
        }
    }
    
}
