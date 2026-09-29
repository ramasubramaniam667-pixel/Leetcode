class Solution {
    public int[] buildArray(int[] nums) {
         int[] vals = new int[nums.length];
         for(int i = 0; i < vals.length; i++){
              vals[i] = nums[nums[i]];
            }
         return vals;
        
    }    
}