package com.amit;

import com.amit.common.LeetCodeUtils;

public class Driver {
    public static void main(String[] args) {
        int target = 7;
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        String name = "Amit Arunita Saxena";
        boolean bool = true;
        User user = new User(
                "Amit",
                "Barclays",
                null);

        LeetCodeUtils.runTest(0, () -> method(target, nums, user, name, bool));
    }

    // CHANGED: Return type is now 'int' to match your expected output of '2'
    public static int method(int target, int[] nums, User user, String name, boolean bool) {
        return 10;
    }
}