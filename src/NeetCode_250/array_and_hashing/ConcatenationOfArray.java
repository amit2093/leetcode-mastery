package NeetCode_250.array_and_hashing;

import leetcode.common.LeetCodeUtils;

public class ConcatenationOfArray {

    public static void main(String[] args) {
        var concatenation = new ConcatenationOfArray();

        int[] nums = {1,4,1,2};
        int[] output = {1,4,1,2,1,4,1,2};
        LeetCodeUtils.runTest(
                output,
                () -> concatenation.getConcatenation(nums)
        );
    }

    public int[] getConcatenation(int[] nums) {
        int[] result = new int[nums.length *  2];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i];
            result[i + nums.length] = nums[i];
        }
        return result;
    }
}
