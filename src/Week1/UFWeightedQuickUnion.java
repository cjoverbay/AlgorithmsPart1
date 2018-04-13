package Week1;

public class UFWeightedQuickUnion implements UF{
    private class WeightedRoot{
        WeightedRoot(int r, int w) {
            root = r;
            weight = w;
        }
        final int root;
        final int weight;
    }
    private final int[] index;
    private final int size;

    public UFWeightedQuickUnion(int s) {
        size = s;
        index = new int[size];
        for (int i = 0; i < size; i++) {
            index[i] = i;
        }
    }

    private WeightedRoot getRoot(int start) {
        int root = index[start];
        int weight = 1;
        while (root != index[root]) {
            weight++;
            root = index[root];
        }
        return new WeightedRoot(root, weight);
    }

    public boolean find(int a, int b) {
        return getRoot(a).root == getRoot(b).root;
    }

    public void union(int a, int b) {
        WeightedRoot aRoot = getRoot(a);
        WeightedRoot bRoot = getRoot(b);
        if (aRoot.weight > bRoot.weight) {
            index[bRoot.root] = aRoot.root;
        } else {
            index[aRoot.root] = bRoot.root;
        }
    }
}
