package Question_NeetCode_LeetCode.Arrays_Hashing;

/**
 * <h2>LeetCode 14: Longest Common Prefix</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given an array of strings {@code strs}, return the longest string that is
 * a prefix of <b>every</b> string in the array. If there is none, return
 * {@code ""}.
 * </p>
 *
 * <h3>Idea: shrink the prefix</h3>
 * <p>
 * Start by assuming the whole first word is the common prefix. Then go through
 * the remaining words one by one. If a word does not start with the current
 * prefix, chop one character off the end of the prefix and test again.
 * Repeat until the word matches, or the prefix becomes empty (then the
 * answer is {@code ""}).
 * </p>
 * <p>
 * The prefix only ever gets shorter, so whatever survives all words is
 * common to all of them.
 * </p>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code strs = ["flower", "flow", "flight"]}</p>
 * <pre>
 *     prefix = "flower"            (start with strs[0])
 *
 *     i = 1, word = "flow"
 *         "flow" starts with "flower"? no  &rarr; prefix = "flowe"
 *         "flow" starts with "flowe"?  no  &rarr; prefix = "flow"
 *         "flow" starts with "flow"?   yes &rarr; move on
 *
 *     i = 2, word = "flight"
 *         "flight" starts with "flow"? no  &rarr; prefix = "flo"
 *         "flight" starts with "flo"?  no  &rarr; prefix = "fl"
 *         "flight" starts with "fl"?   yes &rarr; move on
 *
 *     answer = "fl"
 * </pre>
 *
 * <h3>Early exit</h3>
 * <p>
 * If the prefix shrinks to {@code ""}, no later word can bring it back, so we
 * return {@code ""} immediately.
 * </p>
 *
 * <h3>Complexity</h3>
 * <p>Let {@code n} = number of strings, {@code m} = length of the shortest
 * string (or the first string).</p>
 * <ul>
 *   <li><b>Time: O(n * m)</b>. Worst case every word is compared against a
 *       prefix of length up to {@code m}. Each {@code startsWith} is O(m),
 *       and the prefix shrinks at most {@code m} times in total.</li>
 *   <li><b>Space: O(m)</b>. {@code substring} creates new strings in Java.</li>
 * </ul>
 *
 * <h3>Edge cases</h3>
 * <ul>
 *   <li>Null or empty array returns {@code ""}.</li>
 *   <li>Single word returns that word itself.</li>
 *   <li>No shared first letter, e.g. {@code ["dog", "car"]}, returns {@code ""}.</li>
 *   <li>Identical words return the whole word.</li>
 * </ul>
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0]; // only one word, it is its own prefix

        // Assume the whole first word is the common prefix, shrink as needed
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // Keep chopping the last character until this word starts with prefix
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);

                // Prefix vanished, so there is no common prefix at all
                if (prefix.isEmpty()) return "";
            }
        }

        // Prefix survived every word, so it is common to all of them
        return prefix;
    }
}