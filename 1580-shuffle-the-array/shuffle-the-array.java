class Solution {
    public int[] shuffle(int[] nums, int n) {
        int [] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
             if (i % 2 == 0){
                ans[i] = nums[i/2];
             }
             else ans[i] = nums[n + (i/2)];
        }
         
      return ans;

        
    }
}