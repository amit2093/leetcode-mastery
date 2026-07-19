package leetcode.arrays;

public class LC26_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int totalRemoved = 0;
        int length = nums.length;

        for (int i = 0; i < length; i++){
            for(int j = i + 1; j < length; j++){
                if(nums[i] == nums[j]) {
                    totalRemoved++;
                }
            }
        }

        return length - totalRemoved;
    }
}
