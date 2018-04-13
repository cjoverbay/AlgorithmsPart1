package Week1;

public class UFQuickUnion implements UF{
    private final int[] index;
    private final int size;

    public UFQuickUnion(int s) {
        size = s;
        index = new int[size];
        for (int i = 0; i < size; i++) {
            index[i] = i;
        }
    }

    private int getRoot(int start) {
        int root = index[start];
        while (root != index[root]) {
            root = index[root];
        }
        return root;
    }

    public boolean find(int a, int b) {
        return getRoot(a) == getRoot(b);
    }

    public void union(int a, int b) {
        index[getRoot(a)] = getRoot(b);
    }
}
