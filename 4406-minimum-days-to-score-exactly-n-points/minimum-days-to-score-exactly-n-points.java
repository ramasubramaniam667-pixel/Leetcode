class Solution {
    public int minDays(int n) {
 
        int INF = 1_000_000_000;
        int[] dp = new int[n + 1];

        Arrays.fill(dp, INF);

        dp[0] = -1;

        for (int x = 1; x <= n; x++) {

            for (int k = 1; k * (k + 1) <= 2 * x; k++) {
                int triangular = k * (k + 1) / 2;

                dp[x] = Math.min(
                    dp[x],
                    dp[x - triangular] + k + 1
                );
            }
        }

        return dp[n];
    }
}