package NeetCode_250.sliding_window;

import leetcode.common.LeetCodeUtils;

public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        var sol = new LongestRepeatingCharacterReplacement();
        LeetCodeUtils.runTest(
                4,
                () -> sol.characterReplacement("XYYX", 2)
        );
    }

    public int characterReplacement(String s, int k) {
        int answer = 0;
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
            } else  {
                answer += right - left;
            }
        }

        return  answer;
    }
}
