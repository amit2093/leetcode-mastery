package NeetCode_250.array_and_hashing;

import leetcode.common.LeetCodeUtils;

public class ProductsOfArrayExceptSelf {

    public static void main(String[] args) {
        var solution = new ProductsOfArrayExceptSelf();
        int[] nums = {1,2,4,6};
        int[] output = {48,24,12,8};
        LeetCodeUtils.runTest(
                output,
                () -> solution.productExceptSelf(nums)
        );
    }

    public int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];
        prefix[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        // prefix array is: 1,1,2,8

        int[] suffix = new int[nums.length];
        suffix[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        // suffix array is: 48,24,6,1

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        // result array is: 48,24,12,8

        return result;
    }
}
