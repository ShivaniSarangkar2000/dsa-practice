package forteen;

import java.util.*;
import java.io.*;

public class MaximumExpertNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(br.readLine().trim());
        }

        int[] dp = new int[n + 1];
        // dp[i] = max expert number for first i employees

        for (int i = 0; i < n; i++) {
            int[] freq = new int[n + 2];
            int mex = 0;

            // Extend window from i down to 0
            for (int j = i; j >= 0; j--) {
                int val = A[j];
                if (val <= n) {
                    freq[val]++;
                }
                // Advance mex pointer
                while (freq[mex] > 0) {
                    mex++;
                }
                // dp[j] = best up to index j (exclusive)
                // window is A[j..i], contributed mex
                int candidate = dp[j] + mex;
                if (candidate > dp[i + 1]) {
                    dp[i + 1] = candidate;
                }
            }
        }

        System.out.println(dp[n]);
    }
}