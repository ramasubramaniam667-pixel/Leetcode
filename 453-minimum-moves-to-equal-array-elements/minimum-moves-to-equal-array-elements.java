class Solution {
    public int minMoves(int[] nums) {
          int l = Arrays.stream(nums).min().getAsInt(),moves = 0;
          for (int x : nums) moves += x - l ;
          return moves;
    }
}