package NeetCode_250_Repeat.array_and_hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// https://neetcode.io/problems/duplicate-integer/question?list=neetcode250

// Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.
// Input:  [1, 2, 3, 3]
// Output: true
public class ContainsDuplicate {

    // using map
    public boolean hasDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            } else  {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    // using set
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

    // using set but without else condition
    public boolean containsDuplicate_collapse(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    // using java 8 streams
    public boolean usingStreams(int[] nums) {
        return Arrays.stream(nums).distinct().count() != nums.length;
    }

}
