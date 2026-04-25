import java.util.*;

public class MSSWithSwaps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int maxSum = Integer.MIN_VALUE;

        // Try all subarrays
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                List<Integer> inside = new ArrayList<>();
                List<Integer> outside = new ArrayList<>();

                // Separate inside and outside elements
                for (int x = 0; x < n; x++) {
                    if (x >= i && x <= j) {
                        inside.add(a[x]);
                    } else {
                        outside.add(a[x]);
                    }
                }

                // Sort inside (ascending) and outside (descending)
                Collections.sort(inside);
                outside.sort(Collections.reverseOrder());

                int currentSum = 0;
                for (int val : inside) currentSum += val;

                int tempSum = currentSum;

                // Try up to k swaps
                for (int t = 0; t < k && t < inside.size() && t < outside.size(); t++) {
                    if (outside.get(t) > inside.get(t)) {
                        tempSum += outside.get(t) - inside.get(t);
                    } else {
                        break;
                    }
                }

                maxSum = Math.max(maxSum, tempSum);
            }
        }

        System.out.println(maxSum);
    }
}