package forteen;

import java.util.*;
//2
public class MaxGoodSubarray {

    public static long maxGoodSubarraySum(int[] A, int k) {
        int n = A.length;

        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        long currentSum = 0;
        long maxSum = 0; // empty subarray allowed

        for (int right = 0; right < n; right++) {
            // Add current element
            freq.put(A[right], freq.getOrDefault(A[right], 0) + 1);
            currentSum += A[right];

            // Shrink window if distinct elements exceed k
            while (freq.size() > k) {
                freq.put(A[left], freq.get(A[left]) - 1);
                currentSum -= A[left];

                if (freq.get(A[left]) == 0) {
                    freq.remove(A[left]);
                }
                left++;
            }

            // Update max sum
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int k = sc.nextInt();

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        System.out.println(maxGoodSubarraySum(A, k));
    }
}
