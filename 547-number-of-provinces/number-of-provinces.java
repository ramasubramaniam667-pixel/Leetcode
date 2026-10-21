class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] union = new int[n];
        int count = 0;
        for(int i=0; i<n;i++){
            union[i] = i;
        }

        for(int i=0; i<n;i++) {
            for(int j=0; j<n;j++) {
                if (i==j) continue;
                if (isConnected[i][j] == 1) {
                    int u = find(union, i);
                    int v = find(union, j);
                    if(u != v){
                        union(union, u, v);
                    }
                }

            }
        }
        for(int i=0; i<n;i++){
            if (union[i] == i) count++;
        }
        return count;
    }

    private int find(int[] parent, int i){
        if(parent[i] != i){
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    private void union(int[] parent, int u, int v){
        if(u < v){
            parent[u] = v;
        }
        else{
            parent[v] =u;
        }
    }
}