package forteen;

import java.util.*;

public class MinMovesArmy {
    static Map<Long, Integer> memo = new HashMap<>();

    public static int minMoves(long n) {
        if (n <= 1) return 0;

        if (memo.containsKey(n)) return memo.get(n);

        // Option 1: Make divisible by 2, then divide
        int stepsDiv2 = (int)(n % 2) + 1 + minMoves(n / 2);

        // Option 2: Make divisible by 3, then divide
        int stepsDiv3 = (int)(n % 3) + 1 + minMoves(n / 3);

        int result = Math.min(stepsDiv2, stepsDiv3);

        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        System.out.println(minMoves(n));
    }
}
