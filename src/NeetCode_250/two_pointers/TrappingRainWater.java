package NeetCode_250.two_pointers;

import leetcode.common.LeetCodeUtils;


public class TrappingRainWater {

    public static void main(String[] args) {
        var sol = new TrappingRainWater();
        LeetCodeUtils.runTest(
                9,
                () -> sol.trap2(new int[] {0,2,0,3,1,0,1,3,2,1})
        );
    }

    public int trap(int[] height) {
        int trap = 0;
        int[] prefixMax = new int[height.length];
        int[] suffixMax = new int[height.length];
        prefixMax[0] = height[0];
        suffixMax[height.length - 1] = height[height.length - 1];

        for (int i = 1; i < height.length; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], height[i]);
        }

        for (int i = height.length - 2; i > 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], height[i]);
        }

        for (int i = 1; i < height.length; i++) {
            trap += Math.min(prefixMax[i], suffixMax[i]) - height[i];
        }

        return trap;
    }

    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMaxValue = height[left];
        int rightMaxValue = height[right];
        int res = 0;

        while (left < right) {
            if (leftMaxValue < rightMaxValue) {
                left++;
                leftMaxValue = Math.max(leftMaxValue, height[left]);
                res += leftMaxValue - height[left];
            } else {
                right--;
                rightMaxValue = Math.max(rightMaxValue, height[right]);
                res += rightMaxValue - height[right];
            }
        }
        return res;
    }
}
