package NeetCode_250.sliding_window;

import leetcode.common.LeetCodeUtils;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        var s = new LongestSubstringWithoutRepeatingCharacters();
        LeetCodeUtils.runTest(
                3,
                () -> s.lengthOfLongestSubstring2("zxyzxyz")
        );
    }

    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            map.put(s.charAt(right), right);

            int window = right - left + 1;
            res = Math.max(res, window);
        }
        return res;
    }

    // input 1 = zxyzxyz
    // input 2 = xyzxyz
    // input 3 = xxxx
    public int lengthOfLongestSubstring2(String s) {
        int res = 0;
        int left = 0;

        for (int right = 1; right < s.length(); right++) {
            if (s.charAt(right) == s.charAt(left)) {
                while (right - left + 1 > left) {
                    left++;
                }
            }

            int window = right - left + 1;
            res = Math.max(res, window);
        }

        return res;
    }

}