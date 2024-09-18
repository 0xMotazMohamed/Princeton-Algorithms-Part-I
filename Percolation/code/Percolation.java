import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[] open;
    private WeightedQuickUnionUF uf; // Union-Find for percolation check
    private int virtualTop;
    private int virtualBottom;
    private int openSiteCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        this.open = new boolean[n * n];
        this.uf = new WeightedQuickUnionUF(n * n + 2); // +2 for virtual top and bottom
        this.virtualTop = n * n; // index for virtual top site
        this.virtualBottom = n * n + 1; // index for virtual bottom site
        this.openSiteCount = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = (row - 1) * n + (col - 1);
        if (!open[index]) {
            open[index] = true;
            openSiteCount++;
            // Connection Process :

            // 1) upper and lower relations :

            if (row == 1) uf.union(index, virtualTop);
            // connect to virtual top if in the top row

            if (row == n) uf.union(index, virtualBottom);
            // connect to virtual bottom if in the bottom row

            // 2) neighbors :

            // connect to 4 neighboring open sites to
            if (row > 1 && isOpen(row - 1, col)) uf.union(index, index - n); // top neighbor
            if (row < n && isOpen(row + 1, col)) uf.union(index, index + n); // bottom neighbor
            if (col > 1 && isOpen(row, col - 1)) uf.union(index, index - 1); // left neighbor
            if (col < n && isOpen(row, col + 1)) uf.union(index, index + 1); // right neighbor
        }
    }

    public boolean isOpen(int row, int col) {
        int index = (row - 1) * n + (col - 1);
        return open[index];
        // just to know
    }

    public boolean isFull(int row, int col) {
        int index = (row - 1) * n + (col - 1);
        return uf.find(index) == uf.find(virtualTop);
        // for the blue color check with top point only
    }

    public int numberOfOpenSites() {
        return openSiteCount;
        // returns the number of open sites
    }

    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
        // Checking if top & bottom are connected
    }

}
