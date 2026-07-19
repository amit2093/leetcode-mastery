package NeetCode_250.array_and_hashing;

import leetcode.common.LeetCodeUtils;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        var sol = new GroupAnagrams();
        String[] strs = {"act","pots","tops","cat","stop","hat"};
        String[][] output = {{"hat"},{"act", "cat"},{"stop", "pots", "tops"}};

        LeetCodeUtils.runTest(
                output,
                () -> sol.groupAnagrams(strs)
        );

    }
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs == null || strs.length == 0) return ans;

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }

        return ans;
    }

}
