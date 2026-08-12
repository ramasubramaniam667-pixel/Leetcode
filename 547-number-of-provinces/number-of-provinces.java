class Solution {

    public void dfs(int node, int[][] isConnected, boolean[] visited){

        // Mark current city as visited
        visited[node] = true;

        // Visit all neighbouring cities
        for(int i = 0; i < isConnected.length; i++){

            if(!visited[i] && isConnected[node][i] == 1){
                dfs(i, isConnected, visited);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;

        boolean[] visited = new boolean[n];

        int provinces = 0;

        // Traverse every city
        for(int i = 0; i < n; i++){

            // New province found
            if(!visited[i]){

                dfs(i, isConnected, visited);

                provinces++;
            }
        }

        return provinces;
    }
}