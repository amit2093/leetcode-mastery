package Question_NeetCode_LeetCode.Arrays_Hashing;

import java.util.*;

/**
 * <h2>LeetCode 49: Group Anagrams</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given an array of strings {@code strs}, group the anagrams together.
 * The groups can be returned in any order.
 * </p>
 *
 * <h3>Key observation</h3>
 * <p>
 * Two strings are anagrams if and only if they have the same letters in the
 * same quantities. So if we <b>sort the characters</b> of each word, all
 * anagrams turn into the exact same string. That sorted string can be used
 * as a <b>HashMap key</b> to bucket the words.
 * </p>
 *
 * <h3>Idea</h3>
 * <ol>
 *   <li>For each word, sort its characters to build a {@code key}.</li>
 *   <li>Use {@code Map<String, List<String>>}: key is the sorted string,
 *       value is the list of original words with that key.</li>
 *   <li>The map's values are the answer groups.</li>
 * </ol>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code strs = ["eat", "tea", "tan", "ate", "nat", "bat"]}</p>
 * <pre>
 *     word  &rarr; sorted key
 *     "eat" &rarr; "aet"
 *     "tea" &rarr; "aet"
 *     "tan" &rarr; "ant"
 *     "ate" &rarr; "aet"
 *     "nat" &rarr; "ant"
 *     "bat" &rarr; "abt"
 *
 *     map after processing all words:
 *     "aet" &rarr; ["eat", "tea", "ate"]
 *     "ant" &rarr; ["tan", "nat"]
 *     "abt" &rarr; ["bat"]
 *
 *     result = [["eat","tea","ate"], ["tan","nat"], ["bat"]]
 * </pre>
 *
 * <h3>Complexity</h3>
 * <p>Let {@code n} = number of strings, {@code k} = max string length.</p>
 * <ul>
 *   <li><b>Time: O(n * k log k)</b>. Sorting each word costs {@code k log k}.</li>
 *   <li><b>Space: O(n * k)</b>. The map stores every word plus its key.</li>
 * </ul>
 *
 * <h3>Faster alternative: count-based key</h3>
 * <p>
 * If the strings contain only lowercase letters, build the key from a
 * frequency array of 26 counts (for example {@code "1#0#0#...#"}) instead of
 * sorting. Time becomes <b>O(n * k)</b>, since counting is linear in the word
 * length. Space is still O(n * k).
 * </p>
 *
 * <h3>Edge cases</h3>
 * <ul>
 *   <li>Empty array returns an empty list.</li>
 *   <li>Empty string {@code ""} has key {@code ""}, and all empty strings
 *       group together.</li>
 *   <li>Single word returns one group with that word.</li>
 * </ul>
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;

        // key: sorted letters, value: all original words with those letters
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            // Step 1: build the key by sorting the word's characters
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // Step 2: create the bucket the first time we see this key
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            // Step 3: put the ORIGINAL word (not the sorted one) in its bucket
            map.get(key).add(strs[i]);
        }

        // Step 4: every bucket is one group of anagrams
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            res.add(list);
        }
        return res;
    }
}