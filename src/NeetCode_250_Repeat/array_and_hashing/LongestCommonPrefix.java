package NeetCode_250_Repeat.array_and_hashing;

// https://neetcode.io/problems/longest-common-prefix/question?list=neetcode250

// You are given an array of strings strs. Return the longest common prefix of all the strings.
// If there is no longest common prefix, return an empty string "".
// Example 1:
// Input: strs = ["bat","bag","bank","band"]
// Output: "ba"
// Example 2:
// Input: strs = ["dance","dag","danger","damage"]
// Output: "da"
// Example 3:
// Input: strs = ["neet","feet"]
// Output: ""
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] array) {
        if (array == null || array.length == 0) return "";

        if (array.length == 1) return array[0];

        String prefixString = array[0];

        // starting this loop from 1 because 0th is already my prefix string.
        // so I will check other strings from input array
        for (int i = 1; i < array.length; i++) {
            // here I am checking if other input strings are starting with my 0th index prefix string
            // if not starting - go inside while loop
            while(!array[i].startsWith(prefixString)){

                // here I am reducing my 0th index (prefixString) if other input strings doesn't start with prefix string
                prefixString = prefixString.substring(0, prefixString.length() - 1);

                // at some point, this prefix string will become empty
                // if it becomes empty means nothing is common prefix
                if (prefixString.isEmpty()) return "";
            }
        }

        // if other input strings are starting with my any of my 0th index string (any char which are not sub stringed)
        return prefixString;
    }

    public String longestCommonPrefix_optimal(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        if (strs.length == 1) return strs[0];

        String zeroIndexString = strs[0];
        int len = zeroIndexString.length();
        for (int i = 1; i < strs.length && len > 0; i++) {
            len = Math.min(len, strs[i].length());
            int j = 0;
            while (j < len && zeroIndexString.charAt(j) == strs[i].charAt(j)) {
                j++;
            }
            len = j;                                  // monotonically shrinks
        }
        return zeroIndexString.substring(0, len);     // exactly one allocation
    }
}
