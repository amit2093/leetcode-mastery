package com.amit.sliding_window;

import com.amit.common.LeetCodeUtils;

/**
 * Problem: 713. Subarray Product Less Than K
 * Difficulty: Medium
 * Pattern: Sliding Window (Dynamic/Flexible Window)
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Notes: This is a Flexible Sliding Window problem where the "shrink" condition is the product being greater than or equal to k
 */
public class LC713_SubarrayProductLessThanK_SlidingWindow {

    public static void main(String[] args) {
        var sol = new LC713_SubarrayProductLessThanK_SlidingWindow();
        int[] nums = {10, 5, 2, 6};
        int k = 100;

        System.out.println("\n--- LC 713: Subarray Product < K ---");
        LeetCodeUtils.measureTime(() -> {
            int result = sol.numSubarrayProductLessThanK(nums, k);
            System.out.println("Sample Input: [10, 5, 2, 6], k=100");
            System.out.println("Expected: 8 | Actual: " + result);
        });
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // Edge case: if k is 0 or 1, no product of positive integers can be < k
        if (k <= 1) return 0;

        int left = 0, right = 0, count = 0;
        long product = 1; // Use long if product might exceed Integer.MAX_VALUE

        while (right < nums.length) {
            // 1. Expand: Include the new element
            product *= nums[right];

            // 2. Shrink: While product is >= k, move left pointer
            while (product >= k && left <= right) {
                product /= nums[left];
                left++;
            }

            // 3. Count: The number of valid subarrays ending at 'right'
            // is (right - left + 1)
            count += (right - left + 1);

            right++;
        }

        return count;
    }
}