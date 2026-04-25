package forteen;

import java.util.*;

public class CircularJumpGame {

    public static int minJumps(int N, int X, int Y, int[] A) {
        // convert to 0-based index
        X--;
        Y--;

        boolean[] visited = new boolean[N];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{X, 0}); // {current_position, steps}
        visited[X] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0];
            int steps = curr[1];

            if (pos == Y) return steps;

            int jump = A[pos];

            // move right
            int right = (pos + jump) % N;

            // move left
            int left = (pos - jump % N + N) % N;

            if (!visited[right]) {
                visited[right] = true;
                queue.offer(new int[]{right, steps + 1});
            }

            if (!visited[left]) {
                visited[left] = true;
                queue.offer(new int[]{left, steps + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int X = sc.nextInt();
        int Y = sc.nextInt();

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        System.out.println(minJumps(N, X, Y, A));
    }
}