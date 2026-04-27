import java.util.*;

public class Main3 {

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