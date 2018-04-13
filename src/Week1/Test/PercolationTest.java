package Week1.Test;

import Week1.Percolation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {

    @Test
    public void testOpensSite() {
        final int n = 6;
        final int size = n^2;
        Percolation p = new Percolation(n);

        assertFalse(p.isOpen(3, 4));
        p.open(3, 4);
        assertTrue(p.isOpen(3, 4));
    }

    @Test
    public void testsPercolate() {
        final int n = 3;
        final int size = n^2;
        Percolation p = new Percolation(n);

        assertFalse(p.percolates());
        p.open(1, 3);
        p.open(2, 3);
        assertFalse(p.percolates());
        p.open(3, 3);
        assertTrue(p.percolates());
    }


}