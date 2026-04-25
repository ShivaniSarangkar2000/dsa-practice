package forteen;

import java.util.*;
import java.io.*;

public class Solution11 {

    static int[] tree;
    static int N;

    static void reset() {
        Arrays.fill(tree, 0);
    }

    static void update(int i, int val) {
        i += N;
        tree[i] = Math.max(tree[i], val);
        while (i > 1) {
            i >>= 1;
            tree[i] = Math.max(tree[2 * i], tree[2 * i + 1]);
        }
    }

    static int query(int l, int r) {
        int res = 0;
        l += N;
        r += N + 1;
        while (l < r) {
            if ((l & 1) == 1) { res = Math.max(res, tree[l]); l++; }
            if ((r & 1) == 1) { r--; res = Math.max(res, tree[r]); }
            l >>= 1;
            r >>= 1;
        }
        return res;
    }

    static boolean check(int[] p, int[] order, int n, int m, int k) {
        reset();
        for (int i : order) {
            int l = Math.max(0, i - k);
            int r = Math.min(n - 1, i + k);
            int prev = query(l, r);
            int dpI = prev + 1;
            if (dpI >= m) return true;
            update(i, dpI);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int m = Integer.parseInt(br.readLine().trim());
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(br.readLine().trim());
        }

        if (m == 1) {
            System.out.println(0);
            return;
        }

        // Sort indices by value (ascending)
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> p[a] - p[b]);

        int[] orderArr = new int[n];
        for (int i = 0; i < n; i++) orderArr[i] = order[i];

        // Initialize segment tree
        N = n;
        tree = new int[2 * N];

        // Binary search on k
        int lo = 0, hi = n - 1, ans = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (check(p, orderArr, n, m, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
