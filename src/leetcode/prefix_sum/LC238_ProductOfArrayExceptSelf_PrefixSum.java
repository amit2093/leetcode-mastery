package leetcode.prefix_sum;

import static leetcode.arrays.common.LeetCodeUtils.runTest;

/**
 * Problem: 238. Product of Array Except Self
 * Difficulty: Medium
 * Pattern: Prefix Sum, Prefix & Suffix Products (Pre-computation)
 * Time Complexity: O(n) - Three passes through the array.
 * Space Complexity: O(n) - Due to prefix and suffix arrays.
 * Notes: Computes the product of all elements to the left and right
 * of each index to avoid using the division operator.
 */

public class LC238_ProductOfArrayExceptSelf_PrefixSum {

    public static void main(String[] args) {
        var sol = new LC238_ProductOfArrayExceptSelf_PrefixSum();
        int[] nums = {1, 2, 3, 4};

        runTest(
                new int[]{24, 12, 8, 6},
                () -> sol.productExceptSelf(nums)
        );
    }

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        int[] prefix = new int[length];
        int[] suffix = new int[length];
        prefix[0] = 1;
        suffix[length - 1] = 1;

        for (int i = 1; i < length; i++){
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        // prefix = [1, 1, 2, 6]
        for (int i = length - 2; i >= 0; i--){
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        // suffix = [24, 12, 4, 1]
        for (int i = 0; i < length; i++){
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }
}
