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


import java.util.Scanner;

public class SumOfAllQueriesGCD {

    //Efficient Approach (Right to Left DP)

    static final long MOD = 1_000_000_007;

    public static int solve(int n, int q, int[] a, int[] queries) {

        return 0;
    }

    static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int[] queries = new int[q];
        for (int i = 0; i < q; i++) queries[i] = sc.nextInt();
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
Output

*/