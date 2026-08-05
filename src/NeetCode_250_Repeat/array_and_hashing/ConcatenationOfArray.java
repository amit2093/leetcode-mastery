package NeetCode_250_Repeat.array_and_hashing;

// https://neetcode.io/problems/concatenation-of-array/question?list=neetcode250

// You are given an integer array nums of length n. Create an array ans of length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).
// Specifically, answer is the concatenation of two nums arrays.
// Return the array ans.
// Input:  [1,4,1,2]
// Output: [1,4,1,2,1,4,1,2]
public class ConcatenationOfArray {

    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++){
            ans[i] = nums[i];
            ans[i + nums.length] = nums[i];
        }
        return ans;
    }
}
