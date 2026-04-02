package leetcode.arrays;

import java.util.Arrays;

/**
 * Problem: 217. Contains Duplicate
 * Difficulty: Easy
 * Pattern: Sorting
 * Time Complexity: O(n log n) - Due to the sorting step.
 * Space Complexity: O(1) or O(log n) - Depending on the internal implementation of Arrays.sort().
 *
 * Notes: Sorts the array so that identical elements are adjacent.
 * Then, a single linear pass checks if any two neighbors are the same.
 */
public class LC217_ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }
}
