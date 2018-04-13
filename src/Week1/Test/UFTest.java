package Week1.Test;

import Week1.UF;
import Week1.UFQuickUnion;
import Week1.UFWeightedQuickUnion;
import Week1.UFWeightedQuickUnion2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UFTest {
    private static final Class[] TEST_CLASSES = new Class[]{
            UFQuickUnion.class,
            UFQuickUnion.class,
            UFWeightedQuickUnion.class,
            UFWeightedQuickUnion2.class
    };
    private static final int NUM_CLASSES=4;
    private static final String REPETITION_NAME = "with class {currentRepetition} of {totalRepetitions}";

    private UF getUFInstance(int repetition, int size) throws Exception {
        return (UF)TEST_CLASSES[repetition - 1].getConstructor(int.class).newInstance(size);
    }

    @RepeatedTest(value=NUM_CLASSES, name=REPETITION_NAME)
    public void testUnionFind(RepetitionInfo repetitionInfo) throws Exception{
        final UF uf = getUFInstance(repetitionInfo.getCurrentRepetition(), 9);

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