package forteen;

import java.util.*;

public class MaximumExpertNumber{
//Its not working

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int N = sc.nextInt();
            int[] A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }

            int[] future = new int[1005];
            for (int x : A) future[x]++;

            boolean[] present = new boolean[1005];

            int mex = 0;
            int answer = 0;

            for (int i = 0; i < N; i++) {
                int val = A[i];

                future[val]--;         // remove from future
                present[val] = true;   // add to current segment

                while (present[mex]) {
                    mex++;
                }

                // check if we should cut
                boolean canExtend = false;

                for (int j = 0; j < mex; j++) {
                    if (future[j] > 0) {
                        canExtend = true;
                        break;
                    }
                }

                if (!canExtend) {
                    answer += mex;

                    Arrays.fill(present, false);
                    mex = 0;
                }
            }

            System.out.println(answer);
        }
    }