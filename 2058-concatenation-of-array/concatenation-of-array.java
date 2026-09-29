class Solution {
    public int[] getConcatenation(int[] nums){
         int[] vals = new int[nums.length * 2];
         for(int i = 0 ; i < vals.length; i++ ) {
              vals[i] = nums[i % nums.length];
         }
         return vals ;
    }
}
