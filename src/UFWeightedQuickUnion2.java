public class UFWeightedQuickUnion2 implements UF{
    private final int[] index;
    private final int[] sizes;
    private final int size;

    public UFWeightedQuickUnion2(int s) {
        size = s;
        index = new int[size];
        for (int i = 0; i < size; i++) {
            index[i] = i;
        }
        sizes = new int[size]; // Default to 0
    }

    private int getRoot(int start) {
        int root = index[start];
        while (root != index[root]) {
            index[root] = index[index[root]]; //Path compression
            root = index[root];
        }
        return root;
    }

    public boolean find(int a, int b) {
        return getRoot(a) == getRoot(b);
    }

    public void union(int a, int b) {
        int i = getRoot(a);
        int j = getRoot(b);
        if (i == j) {
            return;
        }
        if(sizes[i] < sizes[j]) {
            index[i] = j;
            sizes[j] += sizes[i];
        } else {
            index[j] = i;
            sizes[i] += sizes[j];
        }
    }
}
