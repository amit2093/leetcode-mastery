package com.amit.sliding_window;

import com.amit.common.LeetCodeUtils;

/**
 * Problem: 1248. Count Number of Nice Subarrays
 * Difficulty: Medium
 * Pattern: Sliding Window (At Most K - At Most K-1) OR Prefix Sum
 * Time Complexity: O(n)
 * Space Complexity: O(1) if using sliding window, O(n) if using Map
 *
 * Notes: "Flexible Sliding Window" or "Prefix Sum + Hash Map"
 */
public class LC1248_CountNumberOfNiceSubarrays_SlidingWindow {

    public static void main(String[] args) {
        var sol = new LC1248_CountNumberOfNiceSubarrays_SlidingWindow();
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;

        System.out.println("\n--- LC 1248: Count Nice Subarrays ---");
        LeetCodeUtils.measureTime(() -> {
            int result = sol.numberOfSubarrays(nums, k);
            System.out.println("Sample Input: [1, 1, 2, 1, 1], k=3");
            System.out.println("Expected: 2 | Actual: " + result);
        });
    }

    public int numberOfSubarrays(int[] nums, int k) {
        // Professional Tip: Solving "Exactly K" is often easier by
        // calculating (At Most K) - (At Most K - 1)
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int left = 0, right = 0, count = 0, res = 0;
        while (right < nums.length) {
            if (nums[right] % 2 != 0) k--;

            while (k < 0) {
                if (nums[left] % 2 != 0) k++;
                left++;
            }
            // All subarrays ending at 'right' and starting between 'left' and 'right'
            res += right - left + 1;
            right++;
        }
        return res;
    }
}
