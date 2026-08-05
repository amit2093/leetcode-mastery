package NeetCode_250.two_pointers;

import leetcode.common.LeetCodeUtils;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        var sol = new ContainerWithMostWater();
        LeetCodeUtils.runTest(
                36,
                () -> sol.maxArea(new int[] {1, 7, 2, 5, 4, 7, 3, 6})
        );
    }

    /*
     * Input: height = [1, 7, 2, 5, 4, 7, 3, 6]
     * Output: 36
     */
    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int res = 0;

        while (left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left); // height * width
            res = Math.max(res, area);

            if (heights[left] <= heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}

