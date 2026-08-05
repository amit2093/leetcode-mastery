package NeetCode_250_Repeat.array_and_hashing;

import java.util.HashMap;
import java.util.Map;

// https://neetcode.io/problems/two-integer-sum/question?list=neetcode250

// Given an array of integers nums and an integer target, return the indices i and j such that nums[i] + nums[j] == target and i != j.
// You may assume that every input has exactly one pair of indices i and j that satisfy the condition.
// Return the answer with the smaller index first.
// Input: nums = [3,4,5,6], target = 7
// Output: [0,1]
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] answer =  new int[2];
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                answer[0] = map.get(target - nums[i]);
                answer[1] = i;
                return answer;
            } else  {
                map.put(nums[i], i);
            }
        }

        return answer;
    }
}
