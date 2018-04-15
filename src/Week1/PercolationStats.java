package Week1;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double[] thresholdResults;
    private double meanThreshold;
    private double varianceThreshold;
    private double confLow;
    private double confHigh;

    public PercolationStats(int n, int trials) throws IllegalArgumentException {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        thresholdResults = new double[trials];
        meanThreshold = 0;

        for(int t = 0; t < trials; t++) {
            int[] randomSelect = new int[n*n];
            for (int i = 0; i < n*n; ++i) {
                randomSelect[i] = i;
            }
            StdRandom.shuffle(randomSelect);

            Percolation p = new Percolation(n);

            int opened = 0;
            for(; opened < n*n; opened++) {
                p.open(randomSelect[opened] / n + 1,randomSelect[opened] % n + 1);
                if (p.percolates()) {
                    break;
                }
            }

            double percolationThreshold = (double)opened / (double) (n*n);
            meanThreshold += percolationThreshold;
            thresholdResults[t] = percolationThreshold;
        }
        meanThreshold = meanThreshold / trials;

        // Calculate variance (standard deviation squared)
        varianceThreshold = 0;
        for (int t = 0; t < trials; t++) {
            varianceThreshold += Math.pow(thresholdResults[t] - meanThreshold, 2);
        }
        varianceThreshold = varianceThreshold / (trials - 1);

        double thresh = 1.96 * Math.sqrt(varianceThreshold) / Math.sqrt(trials);
        confLow = meanThreshold - thresh;
        confHigh = meanThreshold + thresh;
    }

    public double mean() {
        return meanThreshold;
    }

    public double stddev() {
        return Math.sqrt(varianceThreshold);
    }

    public double confidenceLo() {
        return confLow;
    }

    public double confidenceHi() {
        return confHigh;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
    }
}