package forteen;

import java.util.*;
import java.io.*;

public class Solution14 {

    // -------------------------
    // Persistent Segment Tree
    // -------------------------
    // Each node stores count of elements in range
    static int[] lc, rc, cnt;  // left child, right child, count
    static int pstSize = 0;
    static final int MAX_PST = 20_000_000;

    static int newNode() {
        lc[pstSize] = rc[pstSize] = cnt[pstSize] = 0;
        return pstSize++;
    }

    // Insert position `pos` into persistent seg tree, returns new root
    static int insert(int prev, int l, int r, int pos) {
        int node = newNode();
        lc[node] = lc[prev];
        rc[node] = rc[prev];
        cnt[node] = cnt[prev] + 1;
        if (l == r) return node;
        int mid = (l + r) / 2;
        if (pos <= mid) lc[node] = insert(lc[prev], l, mid, pos);
        else            rc[node] = insert(rc[prev], mid+1, r, pos);
        return node;
    }

    // Query: count elements in [ql,qr] using roots ver1 (exclusive) and ver2 (inclusive)
    static int query(int ver1, int ver2, int l, int r, int ql, int qr) {
        if (ql > qr || ver1 == ver2) return 0;
        if (ql <= l && r <= qr) return cnt[ver2] - cnt[ver1];
        int mid = (l + r) / 2;
        int res = 0;
        if (ql <= mid) res += query(lc[ver1], lc[ver2], l, mid, ql, qr);
        if (qr > mid)  res += query(rc[ver1], rc[ver2], mid+1, r, ql, qr);
        return res;
    }

    // -------------------------
    // Tree / DFS state
    // -------------------------
    static int N;
    static List<Integer>[] adj;
    static int[] inA, outA, inB, outB;
    static int timer;

    // Iterative DFS for Euler tour
    static void dfs(int root, int[] in, int[] out) {
        timer = 0;
        int[] stack = new int[N + 1];
        int[] parent = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        int top = 0;
        stack[top++] = root;
        parent[root] = -1;
        while (top > 0) {
            int u = stack[top - 1];
            if (!visited[u]) {
                visited[u] = true;
                in[u] = ++timer;
                for (int v : adj[u]) {
                    if (v != parent[u]) {
                        parent[v] = u;
                        stack[top++] = v;
                    }
                }
            } else {
                top--;
                out[u] = timer;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        int A = Integer.parseInt(br.readLine().trim()); // 1-indexed
        int B = Integer.parseInt(br.readLine().trim()); // 1-indexed

        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            int p = Integer.parseInt(br.readLine().trim());
            if (p != 0) {
                adj[i].add(p);
                adj[p].add(i);
            }
        }

        inA = new int[N + 1]; outA = new int[N + 1];
        inB = new int[N + 1]; outB = new int[N + 1];

        // DFS rooted at A
        dfs(A, inA, outA);
        // DFS rooted at B
        dfs(B, inB, outB);

        // Build persistent segment tree:
        // We process nodes in order of inA (1..N).
        // The i-th version stores all nodes with inA <= i,
        // indexed by their inB value.
        //
        // roots[i] = PST root after inserting all nodes with inA == i
        // So roots[0] = empty, roots[inA[u]] inserts node u by its inB[u].

        lc  = new int[MAX_PST];
        rc  = new int[MAX_PST];
        cnt = new int[MAX_PST];
        pstSize = 1; // 0 is "null node"

        // Map inA rank -> node
        int[] nodeByInA = new int[N + 1]; // nodeByInA[inA[u]] = u
        for (int u = 1; u <= N; u++) nodeByInA[inA[u]] = u;

        int[] roots = new int[N + 1]; // roots[0..N], roots[0] = empty
        roots[0] = newNode(); // empty tree

        for (int i = 1; i <= N; i++) {
            int u = nodeByInA[i];
            roots[i] = insert(roots[i - 1], 1, N, inB[u]);
        }

        // Answer queries
        int Q = Integer.parseInt(br.readLine().trim());
        /* int Col = */ Integer.parseInt(br.readLine().trim()); // always 2

        long MOD = 1_000_000_007L;
        long totalSum = 0;
        long K = 0;

        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long Uraw = Long.parseLong(st.nextToken());
            long Vraw = Long.parseLong(st.nextToken());

            // Apply transformation
            int U = (int)((Uraw + K) % N + 1); // 1-indexed
            int V = (int)((Vraw + K) % N + 1); // 1-indexed

            // Beauty(U, V):
            // Count nodes X such that:
            //   inA[U] <= inA[X] <= outA[U]  (X in subtree of U rooted at A)
            //   inB[V] <= inB[X] <= outB[V]  (X in subtree of V rooted at B)
            //
            // Using PST: query over inA range [inA[U], outA[U]],
            //            counting those with inB in [inB[V], outB[V]]
            long beauty = query(
                    roots[inA[U] - 1], roots[outA[U]],
                    1, N,
                    inB[V], outB[V]
            );

            totalSum = (totalSum + beauty) % MOD;
            K = beauty; // last answer (not modded, fits in int since <= N <= 1e5)
        }

        System.out.println(totalSum);
    }
}