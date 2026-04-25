package forteen;

import java.util.*;

public class GreatChain {

    static final int MOD = 1_000_000_007;

    static long modInverse(long x) {
        return pow(x, MOD - 2);
    }

    static long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    static long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    static long[][] matrixExpo(long[][] base, long exp) {
        int n = base.length;
        long[][] result = new long[n][n];

        for (int i = 0; i < n; i++) result[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) result = multiply(result, base);
            base = multiply(base, base);
            exp >>= 1;
        }
        return result;
    }

    public static int solve(int N, int K, int B, int R) {

        long inv = modInverse(1_000_000);
        long p = B * inv % MOD;
        long q = R * inv % MOD;

        int size = 2 * K + 1;
        int offset = K;

        long[][] T = new long[size][size];

        for (int d = -K; d <= K; d++) {
            int i = d + offset;

            // Blue (d+1)
            if (d + 1 <= K) {
                T[i][i + 1] = p;
            }

            // Red (d-1)
            if (d - 1 >= -K) {
                T[i][i - 1] = q;
            }
        }

        long[][] TN = matrixExpo(T, N);

        // initial dp: only dp[0] = 1
        long[] dp0 = new long[size];
        dp0[offset] = 1;

        // final dp = TN * dp0
        long result = 0;
        for (int i = 0; i < size; i++) {
            result = (result + TN[offset][i] * dp0[i]) % MOD;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int B = sc.nextInt();
        int R = sc.nextInt();

        System.out.println(solve(N, K, B, R));
    }
}