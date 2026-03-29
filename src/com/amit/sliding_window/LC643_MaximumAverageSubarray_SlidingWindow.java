package com.amit.sliding_window;

import com.amit.common.LeetCodeUtils;

/**
 * Problem: 643. Maximum Average Subarray I
 * Difficulty: Easy
 * Pattern: Fixed-Size Sliding Window
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LC643_MaximumAverageSubarray_SlidingWindow {
    public static void main(String[] args) {
        var sol = new LC643_MaximumAverageSubarray_SlidingWindow();
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;

        System.out.println("--- LC 643: Max Average Subarray I ---");
        LeetCodeUtils.measureTime(() -> {
            double result = sol.findMaxAverage(nums, k);
            System.out.println("Sample Input: [1, 12, -5, -6, 50, 3], k=4");
            System.out.println("Expected: 12.75 | Actual: " + result);
        });
    }

    public double findMaxAverage(int[] nums, int k) {
        // 1. Calculate sum of the first 'k' elements (Initial Window)
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        double maxSum = currentSum;

        // 2. Slide the window from index 'k' to the end
        for (int i = k; i < nums.length; i++) {
            // Add the new element entering from the right
            // Subtract the element leaving from the left (i - k)
            currentSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, currentSum);
        }

        // 3. Return the average
        return maxSum / k;
    }
}
