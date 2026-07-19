package NeetCode_250.array_and_hashing;

import leetcode.common.LeetCodeUtils;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        var solution = new LongestCommonPrefix();
        String[] strs = {"bat","bag","bank","band"};
        String output = "ba";

        LeetCodeUtils.runTest(
                output,
                () -> solution.longestCommonPrefix(strs)
        );
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}
