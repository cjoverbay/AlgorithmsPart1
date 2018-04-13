package Week1;

public class UFQuickFind implements UF{
    private final int[] index;
    private final int size;

    public UFQuickFind(int s) {
        size = s;
        index = new int[size];
        for (int i = 0; i < size; i++) {
            index[i] = i;
        }
    }

    public boolean find(int a, int b) {
        return index[a] == index[b];
    }

    public void union(int a, int b) {
        if (find(a, b)) {
            return;
        }

        int groupToOverwrite = index[a];

        for (int i = 0; i < size; i++) {
            if (index[i] == groupToOverwrite) {
                index[i] = index[b];
            }
        }
    }
}
