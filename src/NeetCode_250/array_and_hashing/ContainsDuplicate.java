package NeetCode_250.array_and_hashing;

import leetcode.common.LeetCodeUtils;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public static void main(String[] args) {
        var sol = new ContainsDuplicate();
        int[] nums = {1, 2, 3, 3};

        LeetCodeUtils.runTest(
                true,
                () -> sol.hasDuplicate(nums)
        );
    }

    public boolean hasDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

}
