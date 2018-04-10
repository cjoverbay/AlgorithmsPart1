import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UFTest {
    @Test
    public void testUnionFind(){
        final UF uf = new UFWeightedQuickUnion2(9);

        uf.union(1,2);
        uf.union(3,4);
        uf.union(5,6);
        uf.union(7,8);
        assertFalse(uf.find(8,1));
        uf.union(2,7);
        assertTrue(uf.find(8,1));
        uf.union(0,8);

        assertTrue(uf.find(1,2));
        assertTrue(uf.find(8,1));

    }
}