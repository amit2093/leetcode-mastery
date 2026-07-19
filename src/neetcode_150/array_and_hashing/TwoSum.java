package neetcode_150.array_and_hashing;

import leetcode.common.LeetCodeUtils;

import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {
        var sol = new TwoSum();
        int[] nums = {3,4,5,6};
        int target = 7;
        int[] output = {0,1};

        LeetCodeUtils.runTest(
                output,
                () -> sol.twoSum(nums, target)
        );
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])){
                return new int[] {
                        map.get(target - nums[i]),
                        i
                };
            }
            map.put(nums[i], i);
        }

        return res;
    }
}
