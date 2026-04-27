//Question 1
/*
You are given an array a of N integers and
two integer bounds, 1o and hi, where lo <= hi.
Your task is to count the number of contiguous subarrays
within a
where every element x in the subarray satisfies the condition 1o <= x <= hi.
Find the total count of such subarrays,
modulo 10^9 +7.
 */

/*
Input Format
The first line contains a integer, N, denoting the number of elements in array a.
The second line contains a integer, lo, denoting the lower bound for elements
in a valid subarray.
The third line contains a integer, hi denoting the upper bound for elements
in a valid subarray.
The next line contains N space separated integers a[i]
where(0 <= i < N)
 */

/*
Constraints
1 <= N <= 10^5
1 <= lo <= 10^9
1 <= hi <= 10^9
1 <= a[i] <= 10^9
*/


import java.util.Scanner;

public class TotalCountOfSubArrays {

//Approach 1 - Segment Length Formula

    public static long solve(int N, int lo, int hi, int[] a) {
        final long MOD = 1_000_000_007L;
        long result = 0;
        long count = 0;  // length of current valid streak ending at index i

        for (int i = 0; i < N; i++) {
            if (a[i] >= lo && a[i] <= hi) {
                count++;          // extend the valid streak
            } else {
                count = 0;        // streak broken, reset
            }
            result = (result + count) % MOD;
        }

        return result;
    }
    /*
Another approach - Sliding Window Approach
public static long solve(int N, int lo, int hi, int[] a) {
        long MOD = 1_000_000_007L;
        long count = 0;
        int left = 0;

        for (int right = 0; right < N; right++) {
            if (a[right] < lo || a[right] > hi) {
                left = right + 1;
            }
            count = (count + (right - left + 1)) % MOD;
        }

        return count;
}
 */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int lo = sc.nextInt();
        int hi = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) a[i] = sc.nextInt();

        long result = solve(N, lo, hi, a);
        System.out.println(result);

    }

}

/*
Case Input 1
3
1
3
1 2 3
Output
6
*/

/*
Case Input 2
5
1
3
1 5 2 10 3
Output
3
*/

/*
Case Input 3
5
2
4
1 2 3 5 4
Output
4
*/


