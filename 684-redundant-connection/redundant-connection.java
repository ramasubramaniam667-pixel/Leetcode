class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] ans = new int[]{-1, -1};
        int[] parent = new int[n + 1];

        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }

        for(int[] edge : edges){
            int p1 = findParent(edge[0], parent);
            int p2 = findParent(edge[1], parent);

            if(p1 == p2)
                ans = edge;

            parent[p2] = p1;
        }

        return ans;
    }

    private int findParent(int node, int[] parent){
        if(node == parent[node])
            return node;

        return findParent(parent[node], parent);
    }
}