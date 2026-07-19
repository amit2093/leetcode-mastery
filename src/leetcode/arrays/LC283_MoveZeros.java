package leetcode.arrays;

public class LC283_MoveZeros {

    public void moveZeroes(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 1; i++){
            if(nums[i] == 0) {
                for(int j = i + 1; j < length; j++) {
                    if (nums[j] != 0) {
                        int a = nums[i];
                        int b = nums[j];
                        nums[i] = b;
                        nums[j] = a;
                        break;
                    }
                }
            }
        }
    }
}
