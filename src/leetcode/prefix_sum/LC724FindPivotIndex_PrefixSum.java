package leetcode.prefix_sum;

/**
 * Problem: 724. Find Pivot Index
 * Difficulty: Easy
 * Pattern: Prefix Sum (Space Optimized)
 * Time Complexity: O(n) - Two passes through the array.
 * Space Complexity: O(1) - Constant space used for tracking sums.
 *
 * Notes: Calculates the total sum first, then iterates through the array
 * updating the left and right sums dynamically to find the balance point.
 */
public class LC724FindPivotIndex_PrefixSum {

    public int pivotIndex(int[] nums) {
        if(nums.length == 0) return - 1;
        int leftSum = 0, rightSum = 0;
        for(int num : nums)
            rightSum += num;

        for(int i = 0; i < nums.length; i ++) {
            rightSum -= nums[i];
            if(rightSum == leftSum) return i;
            leftSum += nums[i];
        }
        return - 1;
    }
}
