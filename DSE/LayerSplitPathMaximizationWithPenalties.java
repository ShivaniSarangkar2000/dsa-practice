import java.util.*;

public class LayerSplitPathMaximizationWithPenalties {

    static class Node {
        int id, layer;
        long value;

        Node(int id, int layer, long value) {
            this.id = id;
            this.layer = layer;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int[] layer = new int[N];
        long[] value = new long[N];

        for (int i = 0; i < N; i++) {
            layer[i] = sc.nextInt();
            value[i] = sc.nextLong();
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Sort nodes by layer
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i, layer[i], value[i]);
        }

        Arrays.sort(nodes, Comparator.comparingInt(a -> a.layer));

        long[] dp = new long[N];
        Arrays.fill(dp, Long.MIN_VALUE);

        long answer = Long.MIN_VALUE;

        for (Node node : nodes) {
            int u = node.id;

            // Start new path
            dp[u] = value[u];

            // Check all neighbors
            for (int v : adj.get(u)) {
                if (layer[v] <= layer[u] && dp[v] != Long.MIN_VALUE) {
                    long penalty = (long)(layer[u] - layer[v]) * (layer[u] - layer[v]);
                    dp[u] = Math.max(dp[u], dp[v] + value[u] - penalty);
                }
            }

            answer = Math.max(answer, dp[u]);
        }

        System.out.println(answer);
    }
}