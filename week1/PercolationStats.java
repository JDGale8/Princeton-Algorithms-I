/**
 * Created by Jake on 1/28/2017.
 *
 *
 * This is to calculate the stats for the percolation problem
 */
package week1;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] t;

    public PercolationStats(int n, int trials) {     // perform trials independent experiments on an n-by-n grid
        if(n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        int     row;
        int     col;
        t    =  new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation pc = new Percolation(n);

            while (!pc.percolates()) {
                row = StdRandom.uniform(1, n+1);
                col = StdRandom.uniform(1, n+1);
                pc.open(row, col);
            }

            t[i] = (double)pc.numberOfOpenSites()/(n*n);
        }
    }
    public double mean() {                           // sample mean of percolation threshold
        return StdStats.mean(t);
    }
    public double stddev() {                         // sample standard deviation of percolation threshold
        return StdStats.stddev(t);
    }
    public double confidenceLo() {                   // low  endpoint of 95% confidence interval
        return mean() - 1.96*stddev() / Math.sqrt(t.length);
    }
    public double confidenceHi() {                   // high endpoint of 95% confidence interval
        return mean() + 1.96*stddev() / Math.sqrt(t.length);
    }

    public static void main(String[] args) {        // test client (described below)

        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println("mean   \t\t\t\t\t\t = " + ps.mean());
        System.out.println("stddev \t\t\t\t\t\t = " + ps.stddev());
        System.out.println("95% confidence interval \t = [" + ps.confidenceLo() +", "+ ps.confidenceHi() + "]");
    }
}