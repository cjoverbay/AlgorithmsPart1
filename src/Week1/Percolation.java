package Week1;

//import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private int width;
    private int size;
    private int numOpen;
    private boolean[] isSiteOpen;

    private Percolation() {
    }

    public Percolation(final int w) {
        this.width = w;
        size = w * w;
        numOpen = 0;
        sites = new WeightedQuickUnionUF( size + 2);
        isSiteOpen = new boolean[size + 2];
        initialize();
    }

    private void initialize() {
        isSiteOpen[0] = true;
        isSiteOpen[size + 1] = true;
    }

    public void open(final int row,final int col){
        // Must connect 4 sites

        if (isOpen(row, col)) {
            return;
        }

        final int i = getIndex(row, col);

        unionIfNotConnected(i, Math.max(i - width, 0));
        unionIfNotConnected(i, Math.min(i + width, size + 1));

        if( (i-1) % width != 0) {
            // This is not at the start of the row
            unionIfNotConnected(i,i - 1);
        }

        if( i % width != 0) {
            // This is not at the end of the row
            unionIfNotConnected(i,i + 1);
        }
        numOpen++;
        isSiteOpen[i] = true;
    }

    private void unionIfNotConnected(int a, int b) {
        if (!sites.connected(a, b) && isSiteOpen[b]) {
            sites.union(a, b);
        }
    }

    public boolean isOpen(int row, int col) {
        final int i = getIndex(row, col);
        return isSiteOpen[i];
    }

    public boolean isFull(int row, int col) {
        return sites.connected(0, getIndex(row, col));
    }

    public int numberOfOpenSites() {
        return numOpen;
    }

    public boolean percolates() {
        return sites.connected(0,size + 1);
    }

    private int getIndex(int row, int col){
        return (row - 1) * width + col;
    }
}