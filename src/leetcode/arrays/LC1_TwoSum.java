package leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

import static leetcode.arrays.common.LeetCodeUtils.runTest;

/**
 * Problem: 1. Two Sum
 * Difficulty: Easy
 * Pattern: Hash Map (One-pass)
 * Time Complexity: O(n) - We traverse the list containing n elements only once.
 * Space Complexity: O(n) - The extra space required depends on the number of items stored in the hash map.
 *
 * Notes: Uses a hash map to store the value and its index. For each element,
 * we check if its complement (target - current) already exists in the map.
 */
public class LC1_TwoSum {
    public static void main(String[] args) {
        var sol = new LC1_TwoSum();
        int[] nums = {3,2,3};
        int target = 6;

        runTest(
                new int[]{0,2},
                () -> sol.twoSum(nums, target)
        );
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i);
        }
        return new int[]{};
    }

}
