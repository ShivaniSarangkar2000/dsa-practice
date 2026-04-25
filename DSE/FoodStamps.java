import java.util.*;

public class FoodStamps {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();//10^5
        long m = sc.nextLong();//10^9

        long[] v = new long[n];
        long[] d = new long[n];

        for (int i = 0; i < n; i++) v[i] = sc.nextLong();
        for (int i = 0; i < n; i++) d[i] = sc.nextLong();

        long left = 0, right = (long)1e18;

        // Binary search for threshold T
        while (left < right) {
            long mid = (left + right + 1) / 2;

            long count = 0;

            for (int i = 0; i < n; i++) {
                if (v[i] >= mid) {
                    count += (v[i] - mid) / d[i] + 1;
                    if (count > m) break;
                }
            }

            if (count >= m) left = mid;
            else right = mid - 1;
        }

        long T = left;

        long totalCount = 0;
        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            if (v[i] >= T) {
                long k = (v[i] - T) / d[i] + 1;

                totalCount += k;

                // sum of AP
                totalSum += k * (2 * v[i] - (k - 1) * d[i]) / 2;
            }
        }

        // remove extra terms if we exceeded M
        long extra = totalCount - m;
        totalSum -= extra * T;

        System.out.println(totalSum);
    }
}