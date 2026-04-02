package com.amit.prefix_sum;

/**
 * Problem: 303. Range Sum Query - Immutable
 * Difficulty: Easy
 * Pattern: Prefix Sum
 * Time Complexity:
 * - Constructor: O(n) to build the prefix sum array.
 * - sumRange: O(1) per query.
 * Space Complexity: O(n) to store the prefix sum array.
 *
 * Notes: Precomputes cumulative sums to allow range sum queries in constant time.
 */
public class LC303_RangeSumQueryImmutable_PrefixSum {
    int[] prefixSum;

    public LC303_RangeSumQueryImmutable_PrefixSum(int[] nums) {
        prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];

        for (int i = 1; i < nums.length; i++){
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (prefixSum == null) return -1;
        if (left == 0) return prefixSum[right];
        return prefixSum[right] - prefixSum[left - 1];
    }
}
