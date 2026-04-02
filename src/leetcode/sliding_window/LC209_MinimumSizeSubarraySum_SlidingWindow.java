package leetcode.sliding_window;

import leetcode.common.LeetCodeUtils;

/**
 * Problem: 209. Minimum Size Subarray Sum
 * Difficulty: Medium
 * Pattern: Sliding Window (Variable/Shrinkable)
 * Time Complexity: O(n) - Each element is visited at most twice.
 * Space Complexity: O(1)
 *
 * Notes: Variable-Size Sliding Window
 */

public class LC209_MinimumSizeSubarraySum_SlidingWindow {

    public static void main(String[] args) {
        System.setProperty("full", "true");

        var sol = new LC209_MinimumSizeSubarraySum_SlidingWindow();
        int target = 7;
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        LeetCodeUtils.runTest(2, () -> sol.minSubArrayLen(target, nums));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            // 1. Expand: Add the current element to the window
            currentSum += nums[right];

            // 2. Shrink: While the condition is met, try to find a smaller window
            while (currentSum >= target) {
                // Update result: right - left + 1 is the current window size
                minLength = Math.min(minLength, right - left + 1);

                // Subtract the left element and move the pointer
                currentSum -= nums[left];
                left++;
            }
        }

        // 3. Result: If minLength was never updated, return 0 per LeetCode specs
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }

}

