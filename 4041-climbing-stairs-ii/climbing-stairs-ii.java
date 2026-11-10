class Solution {
    public int climbStairs(int n, int[] costs) {
         int [] dp = new int[n+1];
         Arrays.fill(dp, Integer.MAX_VALUE);
         dp[0] = 0;
         for(int i = 0; i <= n; i++){
            for(int j = 0;j< i && j < 3; j++){
                int m = i - j;
                int s = i - m+1;
                dp[i] = Math.min(dp[i],dp[i-s] + costs[i-1] + (s*s));
            }
         }
         return dp[n];
        
    }
}