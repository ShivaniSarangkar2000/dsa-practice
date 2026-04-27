/*
You have N tasks and P phases.
Task i assigned to phase p contributes value v[i][p]
and takes duration d[i][p].
Each phase runs sequentially, within a phase, tasks
run in parallel.
The duration of a phase equals the maximum task duration in that phase.
Each task must be assigned to exactly one phase.
The total project duration is the sum of phase durations.
Maximum total value subject to total project duration <= T.
Find the maximum total value -1 if no valid assignment exists.

*/

/*
Input format
The first line contains a integer, n, denoting the number of tasks.
The second line contains a integer, p, denoting the number of phases.
The third line contains a integer, t, denoting the maximum allowed total project duration.
Each of the n lines contains p space-separated integers, representing row i of v.
Each of the n lines contains p space-separated integers, representing row i of d.
 */

/*
Constraints
1 <= n <= 8
1 <= p <= 4
1 <= t <= 10^2
1 <= v[i][j] <= 20
1 <= d[i][j] <= 20
 */

import java.util.Scanner;

public class MaximumTotalVauleTaskPhases {

//small constraints + brute force with pruning / DP problem.
//Total assignments = 65536
    //Optimized Backtracking

    static int ans = -1;

    private static void dfs(int task, int n, int p, int t,
                            int[][] v, int[][] d,
                            int[] maxDur, int[] sumVal) {

        if (task == n) {
            int totalDuration = 0;
            int totalValue = 0;

            for (int ph = 0; ph < p; ph++) {
                totalDuration += maxDur[ph];
                totalValue += sumVal[ph];
            }

            if (totalDuration <= t) {
                ans = Math.max(ans, totalValue);
            }
            return;
        }
        for (int ph = 0; ph < p; ph++) {

            int oldMax = maxDur[ph];
            int oldVal = sumVal[ph];

            // update
            maxDur[ph] = Math.max(maxDur[ph], d[task][ph]);
            sumVal[ph] += v[task][ph];

            dfs(task + 1, n, p, t, v, d, maxDur, sumVal);

            // backtrack
            maxDur[ph] = oldMax;
            sumVal[ph] = oldVal;
        }
    }

        private static int solve(int n, int p, int t, int[][] v, int[][] d) {
        //write your code here
            int[] maxDur = new int[p];
            int[] sumVal = new int[p];

            ans = -1;
            dfs(0, n, p, t, v, d, maxDur, sumVal);

            return ans;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int t = sc.nextInt();

        int[][] v = new int[n][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                v[i][j] = sc.nextInt();

        int[][] d = new int[n][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                d[i][j] = sc.nextInt();

        System.out.println(solve(n, p, t, v, d));
    }
}
/*
Case Input 1
3
2
10
5 10
8 2
4 6
4 6
5 3
2 5
Output
19
*/
