package forteen;

import java.util.*;
//1.
public class ArrayQuery {
    static final long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long[] A = new long[n];

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextLong();
        }

        int q = sc.nextInt();
        long answer = 0;

        while (q-- > 0) {
            int type = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();

            if (type == 1) {
                long base = A[l];
                for (int i = l; i <= r; i++) {
                    A[i] = ((i - l + 1) * base) % MOD;
                }
            } else {
                long sum = 0;
                for (int i = l; i <= r; i++) {
                    sum = (sum + A[i]) % MOD;
                }
                answer = (answer + sum) % MOD;
            }
        }

        System.out.println(answer);
    }
}