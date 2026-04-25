import java.util.Scanner;

public class LockAndParity {

    public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            int N = sc.nextInt();
            int[] L = new int[N];

            for (int i = 0; i < N; i++) {
                L[i] = sc.nextInt();
            }

            int minEven = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {

                    if (L[i] != L[j]) {
                        int cost = Math.abs(L[i] - L[j]);

                        if (cost % 2 == 0) {
                            minEven = Math.min(minEven, cost);
                        }
                    }
                }
            }

            if (minEven == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(minEven);
            }
    }
}
