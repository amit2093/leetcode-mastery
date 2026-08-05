package NeetCode_250_Repeat.array_and_hashing;

import java.util.*;

// https://neetcode.io/problems/anagram-groups/question?list=neetcode250

// Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.
// An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
// Example 1:
// Input: strs = ["act","pots","tops","cat","stop","hat"]
// Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
// Example 2:
// Input: strs = ["x"]
// Output: [["x"]]
// Example 3:
// Input: strs = [""]
// Output: [[""]]
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();

        if (strs == null || strs.length == 0) return ans;

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars); // this will make a string with sorted chars
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(str);
        }
        for (String key : map.keySet()) {
            ans.add(new ArrayList<>(map.get(key)));
        }
        return ans;
    }
}
