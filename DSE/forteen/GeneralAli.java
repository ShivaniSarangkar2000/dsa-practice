package forteen;
import java.util.*;
public class GeneralAli {

        static class Pair {
            int x, y;
            Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public static int minTimeToInvade(int N, int M, char[][] grid) {
            Queue<Pair> queue = new LinkedList<>();
            int enemyCount = 0;

            // Step 1: Add all 'A' to queue and count 'E'
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 'A') {
                        queue.offer(new Pair(i, j));
                    } else if (grid[i][j] == 'E') {
                        enemyCount++;
                    }
                }
            }

            // If no enemy
            if (enemyCount == 0) return 0;

            int time = 0;
            int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};

            // Step 2: BFS
            while (!queue.isEmpty()) {
                int size = queue.size();
                boolean spread = false;

                for (int i = 0; i < size; i++) {
                    Pair p = queue.poll();

                    for (int[] d : directions) {
                        int nx = p.x + d[0];
                        int ny = p.y + d[1];

                        if (nx >= 0 && ny >= 0 && nx < N && ny < M
                                && grid[nx][ny] == 'E') {

                            grid[nx][ny] = 'A';
                            queue.offer(new Pair(nx, ny));
                            enemyCount--;
                            spread = true;
                        }
                    }
                }

                // Only increment time if spread happened
                if (spread) time++;
            }

            return enemyCount == 0 ? time : -1;
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int N = sc.nextInt();
            int M = sc.nextInt();
            sc.nextLine();

            char[][] grid = new char[N][M];

            for (int i = 0; i < N; i++) {
                String row = sc.nextLine();
                grid[i] = row.toCharArray();
            }

            int result = minTimeToInvade(N, M, grid);
            System.out.println(result);
        }
    }

