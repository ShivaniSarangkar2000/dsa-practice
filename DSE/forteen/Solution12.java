package forteen;

import java.util.*;
import java.io.*;

public class Solution12
{

    static int[][] sparse;
    static int LOG;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int[] A = new int[N];
        int[] Bonus = new int[N];

        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < N; i++)
            Bonus[i] = Integer.parseInt(br.readLine().trim());

        // Build Sparse Table for Range Max Query on Bonus
        LOG = 1;
        while ((1 << LOG) <= N) LOG++;

        sparse = new int[LOG][N];
        sparse[0] = Bonus.clone();

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i + (1 << j) <= N; i++) {
                sparse[j][i] = Math.max(sparse[j-1][i], sparse[j-1][i + (1 << (j-1))]);
            }
        }

        // Group positions by value: pos_by_val[v] = sorted list of indices where A[idx] == v
        int maxVal = N / 2;
        List<List<Integer>> posByVal = new ArrayList<>();
        for (int v = 0; v <= maxVal; v++)
            posByVal.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            if (A[i] <= maxVal)
                posByVal.get(A[i]).add(i); // positions are added in sorted order naturally
        }

        long totalXP = 0;

        for (int i = 0; i < N; i++) {
            int ai = A[i];
            int bestR = N; // sentinel: not found

            // Iterate over all multiples of ai up to maxVal
            for (int multiple = ai; multiple <= maxVal; multiple += ai) {
                List<Integer> positions = posByVal.get(multiple);
                // Binary search for first position > i
                int lo = 0, hi = positions.size();
                while (lo < hi) {
                    int mid = (lo + hi) / 2;
                    if (positions.get(mid) > i) hi = mid;
                    else lo = mid + 1;
                }
                if (lo < positions.size()) {
                    bestR = Math.min(bestR, positions.get(lo));
                }
            }

            if (bestR < N) {
                totalXP += rangeMax(i, bestR);
            }
        }

        System.out.println(totalXP);
    }

    static int rangeMax(int l, int r) {
        int length = r - l + 1;
        int k = 31 - Integer.numberOfLeadingZeros(length); // floor(log2(length))
        return Math.max(sparse[k][l], sparse[k][r - (1 << k) + 1]);
    }
}