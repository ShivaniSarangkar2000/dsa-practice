//Question 2
/*
You are given an array a of size n and q queries
in each query you are given an integer x
you're task is to find the sum of the shortest segment
that starts at index x such that the GCD of its elements is
equal to 1.
if no segment exist the answer to the query is 0.
GCD(a[i]...a[j]) is the Greatest Common Divisor
of all elements between a[i] and a[j] including a[i] and a[j].
Find the sum of all queries modulo 10^9 + 7.
 */

/*
Input Format
The first line contains a integer, n, denoting the number of elements in a.
The second line contains integer, q, denoting the number of queries.
Each line i of the n subsequent lines where(0 <= i < n) contains a integer, a[i].
Each line i of the q subsequent lines where(0 <= i < q) contains a integer, queries[i].
*/

/*
Constraints

1 <= n <= 10^5
1 <= q <= 10^5
-10^9 <= a[i] <= 10^9
-10^9 <= queries[i] <= 10^9
*/


import java.util.List;
import java.util.Scanner;

public class SumOfAllQueriesGCD {

    //Efficient Approach (Right to Left DP)
    static final long MOD = 1_000_000_007;

    // Sparse table for range GCD
    static int[][] sparse;
    static int[] log2;

    static void buildSparseTable(int[] a, int n) {
        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        sparse = new int[LOG][n];
        log2 = new int[n + 1];

        // Precompute logs
        log2[1] = 0;
        for (int i = 2; i <= n; i++)
            log2[i] = log2[i / 2] + 1;

        // Base case
        for (int i = 0; i < n; i++)
            sparse[0][i] = Math.abs(a[i]);

        // Fill table
        for (int j = 1; j < LOG; j++)
            for (int i = 0; i + (1 << j) <= n; i++)
                sparse[j][i] = gcd(sparse[j-1][i], sparse[j-1][i + (1 << (j-1))]);
    }

    static int queryGCD(int l, int r) {
        int k = log2[r - l + 1];
        return gcd(sparse[k][l], sparse[k][r - (1 << k) + 1]);
    }

    static int gcd(int a, int b) {
        a = Math.abs(a); b = Math.abs(b);
        while (b != 0) { int t = b; b = a % b; a = t; }
        return a;
    }

    public static int solve(int n, int q, int[] a, int[] queries) {

        buildSparseTable(a, n);

        // No MOD during prefix build — apply only at segment sum step
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + a[i]; // ← no MOD here

        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            // Binary search for smallest j >= i such that gcd(a[i..j]) == 1
            if (queryGCD(i, n - 1) != 1) continue; // no valid segment from i

            int lo = i, hi = n - 1, best = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (queryGCD(i, mid) == 1) {
                    best = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            if (best != -1)
                ans[i] = (prefix[best + 1] - prefix[i] + MOD) % MOD;
        }

        // Answer queries
//        long totalSum = 0;
//        for (int i = 0; i < q; i++) {
//            int x = queries[i];
//            if (x >= 0 && x < n)
//                totalSum = (totalSum + ans[x]) % MOD;
        long totalSum = 0;
        for (int i = 0; i < q; i++) {
            int x = queries[i] - 1; // convert 1-based query to 0-based index
            if (x >= 0 && x < n)
                totalSum = (totalSum + ans[x]) % MOD;
        }

        return (int) (totalSum % MOD);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int[] queries = new int[q];
        for (int i = 0; i < q; i++) queries[i] = sc.nextInt();

        System.out.println(solve(n, q, a, queries));
    }
}

/*
Case Input 1
1
1
1
1
Output
1
*/

/*
Case Input 2
2
2
1
1
2
Output
2
*/