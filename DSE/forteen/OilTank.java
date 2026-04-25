package forteen;
import java.util.*;

public class OilTank {

    public static int minInitialOil(int N, int C, int[] A) {
        int prefix = 0;
        int minPrefix = 0;
        int maxPrefix = 0;

        for (int i = 0; i < N; i++) {
            prefix += A[i];
            minPrefix = Math.min(minPrefix, prefix);
            maxPrefix = Math.max(maxPrefix, prefix);
        }

        int low = Math.max(0, -minPrefix);
        int high = C - maxPrefix;

        // If valid range exists
        if (low <= high) {
            return low;
        } else {
            // No perfect solution, best possible
            return low;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int C = sc.nextInt();

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        int result = minInitialOil(N, C, A);
        System.out.println(result);
    }
}
