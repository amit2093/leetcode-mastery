package com.amit.sliding_window;

import com.amit.common.LeetCodeUtils;

/**
 * Problem: 1004. Max Consecutive Ones III
 * Difficulty: Medium
 * Pattern: Sliding Window (Variable with K-quota)
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Notes: This is a Variable-Size Sliding Window problem. The core logic is to maintain a window that contains at most k zeros.
 */
public class LC1004_MaxConsecutiveOnesIII_SlidingWindow {

    public static void main(String[] args) {
        var sol = new LC1004_MaxConsecutiveOnesIII_SlidingWindow();
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;

        System.out.println("\n--- LC 1004: Max Consecutive Ones III ---");
        LeetCodeUtils.measureTime(() -> {
            int result = sol.longestOnes(nums, k);
            System.out.println("Sample Input: [1,1,1,0,0,0,1,1,1,1,0], k=2");
            System.out.println("Expected: 6 | Actual: " + result);
        });
    }

    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right;
        int zeroCount = 0;
        int maxLen = 0;

        for (right = 0; right < nums.length; right++) {
            // 1. Expand: If we see a zero, increment our zero counter
            if (nums[right] == 0) {
                zeroCount++;
            }

            // 2. Shrink: If we've used more than 'k' flips, move 'left'
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // 3. Update Result: Window size is (right - left + 1)
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}