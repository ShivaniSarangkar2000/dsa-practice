package forteen;

import java.util.Scanner;

public class DSUInternalCounting7 {
    static int[] parent, size, adj;

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }
    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;

        // union by size
        if (size[pa] < size[pb]) {
            int temp = pa;
            pa = pb;
            pb = temp;
        }
        parent[pb] = pa;

        // merge sizes
        size[pa] += size[pb];

        // merge adjacent count
        adj[pa] += adj[pb];

        // check if new adjacent pair formed
        if (Math.abs(a - b) == 1) {
            adj[pa]++;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();
        int t = sc.nextInt(); // unused but present

        parent = new int[n + 1];
        size = new int[n + 1];
        adj = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
            adj[i] = 0;
        }

        long result = 0;

        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (type == 1) {
                union(u, v);
            } else {
                int root = find(u);
                int beauty = size[root] - adj[root];
                result += beauty;
            }
        }

        System.out.println(result);
    }
}
