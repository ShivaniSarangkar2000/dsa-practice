package forteen;

import java.util.*;
import java.io.*;

public class Solution13 {

    static int[] tree;
    static int size;

    static void update(int pos) {
        for (; pos <= size; pos += pos & (-pos))
            tree[pos]++;
    }

    static int query(int pos) {
        int s = 0;
        for (; pos > 0; pos -= pos & (-pos))
            s += tree[pos];
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[] A = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(br.readLine().trim());

        final int MOD = 1_000_000_007;

        // Compute f[i] = freq(1..i+1, A[i]) - floor(distinct(1..i+1) / 2)
        int[] f = new int[N];
        Map<Integer, Integer> countLeft = new HashMap<>();
        int distinctLeft = 0;
        for (int i = 0; i < N; i++) {
            int prev = countLeft.getOrDefault(A[i], 0);
            if (prev == 0) distinctLeft++;
            countLeft.put(A[i], prev + 1);
            f[i] = (prev + 1) - (distinctLeft / 2);
        }

        // Compute g[j] = freq(j..N, A[j]) - floor(distinct(j..N) / 2)
        int[] g = new int[N];
        Map<Integer, Integer> countRight = new HashMap<>();
        int distinctRight = 0;
        for (int j = N - 1; j >= 0; j--) {
            int prev = countRight.getOrDefault(A[j], 0);
            if (prev == 0) distinctRight++;
            countRight.put(A[j], prev + 1);
            g[j] = (prev + 1) - (distinctRight / 2);
        }

        // Coordinate compress f values
        int[] sortedF = Arrays.stream(f).distinct().sorted().toArray();
        size = sortedF.length;
        Map<Integer, Integer> compress = new HashMap<>();
        for (int i = 0; i < sortedF.length; i++)
            compress.put(sortedF[i], i + 1); // 1-indexed

        tree = new int[size + 2];

        // Count pairs (i < j) where f[i] + g[j] <= 0  =>  f[i] <= -g[j]
        long ans = 0;
        for (int j = 0; j < N; j++) {
            int threshold = -g[j];
            // Count inserted f[i] values <= threshold
            int idx = upperBound(sortedF, threshold);
            if (idx > 0)
                ans = (ans + query(idx)) % MOD;
            // Insert f[j] into Fenwick tree
            update(compress.get(f[j]));
        }

        System.out.println(ans);
    }

    // Returns number of elements in sortedF that are <= val
    static int upperBound(int[] arr, int val) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= val) lo = mid + 1;
            else hi = mid;
        }
        return lo; // lo elements are <= val
    }
}
