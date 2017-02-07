/**
 * Created by Jake on 1/23/2017.
 *
 * Due 29/1/2017
 *
 * Test to see what the percolation probability of a system is
 * Uses the Algs4 library
 */
package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private  WeightedQuickUnionUF     qU;
    private                 int[]     status;       // status of each cell in bits (0010) = open, (0001) = closed etc
    private                   int     n;
    private                   int     virtualTop;
    private                   int     virtualBot;
    private                   int     openCount = 0;

    // set status bits
    private int closed  = 1;  // (0001)
    private int   open  = 2;  // (0010) open
    private int    ctt  = 4;  // (0100) connected to the top


    public Percolation(int m) {
        if (m <= 0) {
            throw new IllegalArgumentException();
        }

        n           = m;
        virtualTop  = 0;
        virtualBot  = n*n+1;
        status      = new int[n*n];
        qU          = new WeightedQuickUnionUF(n*n+2);

        for (int i = 0; i < n*n+2; i++) {
            if (i < n*n) { status[i] = closed; }  // CLOSED
        }
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (row > n || col > n || row <= 0 || col <= 0) {
            throw new IndexOutOfBoundsException();
        }

        // In order to work to convention, row, col will be given in a 1st index = 1 format
        int      statusIndex    =  n*(row-1) + col - 1;
        int      mainIndex      =  statusIndex + 1;

        if (isOpen(row, col)) return;

        status[statusIndex]     =  2; // set the status to open

        if (row == 1) {
            qU.union(mainIndex, virtualTop);
            status[statusIndex] += ctt;
        }

        if (row == n) {
            qU.union(mainIndex, virtualBot);
        }

        if (row > 1) {
            if (isOpen(row-1, col)) {

                int oldRootStatus   = status[qU.find(mainIndex) - 1];           // get status of current root
                int newRootStatus   = status[qU.find(mainIndex - n) - 1];    // get status of target root
                int combinedStatus  = oldRootStatus | newRootStatus;

                // or operator adds the two status bits together
                // e.g. (0100) or (0010) = (0110)

                // each bit represents a status, and the 4 bit integer will tell us a combination

                status[qU.find(mainIndex) - 1] = status[qU.find(mainIndex - n) - 1] = combinedStatus;

                qU.union(mainIndex, mainIndex - n);
            }
        }
        if (row < n) {
            if (isOpen(row+1, col)) {

                int oldRootStatus   = status[qU.find(mainIndex) - 1];
                int newRootStatus   = status[qU.find(mainIndex + n) - 1];
                int combinedStatus  = oldRootStatus | newRootStatus;

                status[qU.find(mainIndex) - 1] = status[qU.find(mainIndex + n) - 1] = combinedStatus;
                qU.union(mainIndex, mainIndex + n);
            }
        }
        if (col > 1) {
            if (isOpen(row, col-1)) {

                int oldRootStatus   = status[qU.find(mainIndex) - 1];
                int newRootStatus   = status[qU.find(mainIndex - 1) - 1];
                int combinedStatus  = oldRootStatus | newRootStatus;

                status[qU.find(mainIndex) - 1] = status[qU.find(mainIndex - 1) - 1] = combinedStatus;
                qU.union(mainIndex, mainIndex - 1);
            }
        }
        if (col < n) {
            if (isOpen(row, col+1)) {

                int oldRootStatus   = status[qU.find(mainIndex) - 1];
                int newRootStatus   = status[qU.find(mainIndex + 1) - 1];
                int combinedStatus  = oldRootStatus | newRootStatus;

                status[qU.find(mainIndex) - 1] = status[qU.find(mainIndex + 1) - 1] = combinedStatus;
                qU.union(mainIndex, mainIndex + 1);
            }
        }
        openCount++;
    }


    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if (row > n || col > n || row <= 0 || col <= 0) {
            throw new IndexOutOfBoundsException();
        }

            int     index     =  n*(row-1) + col - 1;
            int statusBit     =  status[index] & open;
         return statusBit    ==  open;
    }


    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row > n || col > n || row <= 0 || col <= 0) {
            throw new IndexOutOfBoundsException();
        }
        int     index = n*(row-1) + col;

        // is root connected to the top?
        int     statusBit = status[qU.find(index) - 1] & ctt;
        return  statusBit == ctt;
    }


    public int numberOfOpenSites()           // number of open sites
    {
        return openCount;
    }


    public boolean percolates()              // does the system percolate?
    {
        return qU.connected(virtualBot, virtualTop);
    }


}
