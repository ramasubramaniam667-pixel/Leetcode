
import java.util.*;

class Solution {

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {

        int n = amount.length;

        // STEP 1: Build adjacency list
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        // STEP 2: Find parent and depth using BFS
        int[] parent = new int[n];
        int[] depth = new int[n];

        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {

            int node = queue.poll();

            for (int next : graph[node]) {

                if (next != parent[node]) {

                    parent[next] = node;
                    depth[next] = depth[node] + 1;

                    queue.offer(next);
                }
            }
        }

        // STEP 3: Calculate Bob's path
        int current = bob;
        int bobTime = 0;

        while (current != -1) {

            if (bobTime < depth[current]) {
                amount[current] = 0;
            }
            else if (bobTime == depth[current]) {
                amount[current] /= 2;
            }

            current = parent[current];
            bobTime++;
        }

        // STEP 4: Find Alice's maximum profit using DFS
        int maxProfit = Integer.MIN_VALUE;

        // Stack stores: node, parent, current profit
        int[][] stack = new int[n][3];

        int top = 0;

        stack[top][0] = 0;
        stack[top][1] = -1;
        stack[top][2] = amount[0];

        while (top >= 0) {

            int node = stack[top][0];
            int prev = stack[top][1];
            int profit = stack[top][2];

            top--;

            // Check whether node is a leaf
            if (node != 0 && graph[node].size() == 1) {

                maxProfit = Math.max(maxProfit, profit);
                continue;
            }

            // Explore children
            for (int next : graph[node]) {

                if (next != prev) {

                    stack[++top][0] = next;
                    stack[top][1] = node;
                    stack[top][2] = profit + amount[next];
                }
            }
        }

        return maxProfit;
    }
}
