class Solution {
    public int[][] cyclicShift(int n, int[][] grid, int[] row, int[] col) {
        int ans[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Undo the column shift: find which row this value
                // was in before columns were shifted upward
                int r = (i + col[j]) % n;

                // Undo the row shift: find which column this value
                // was in before rows were shifted left
                int c = (j + row[r]) % n;

                ans[i][j] = grid[r][c];
            }
        }

        return ans;
    }
}