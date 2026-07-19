package NeetCode_250.array_and_hashing;

import leetcode.common.LeetCodeUtils;

import java.util.Arrays;

public class ValidAnagram {

    public static void main(String[] args) {
        var sol = new  ValidAnagram();
        String s = "racecar", t = "carrace";
        LeetCodeUtils.runTest(
                true,
                () -> sol.isAnagram(s, t)
        );
    }

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
    }
}
