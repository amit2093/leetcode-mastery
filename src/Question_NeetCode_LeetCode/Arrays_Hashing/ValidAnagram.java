package Question_NeetCode_LeetCode.Arrays_Hashing;

import java.util.Arrays;

/**
 * <h2>LeetCode 242: Valid Anagram</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given two strings {@code s} and {@code t}, return {@code true} if
 * {@code t} is an anagram of {@code s}, and {@code false} otherwise. An
 * anagram uses exactly the same letters in the same quantities, in any order.
 * </p>
 *
 * <h3>Idea (sort and compare)</h3>
 * <p>
 * If two strings are anagrams, sorting the characters of each one produces
 * the <b>same</b> sequence. So:
 * </p>
 * <ol>
 *   <li>If the lengths differ, they cannot be anagrams, return false.</li>
 *   <li>Convert both strings to {@code char[]} and sort them.</li>
 *   <li>Compare the two sorted arrays with {@code Arrays.equals}.</li>
 * </ol>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code s = "anagram"}, {@code t = "nagaram"}</p>
 * <pre>
 *     s sorted : a a a g m n r
 *     t sorted : a a a g m n r
 *                equal &rarr; true
 *
 *     Example: {@code s = "rat"}, {@code t = "car"}
 *     s sorted : a r t
 *     t sorted : a c r
 *                not equal &rarr; false
 * </pre>
 *
 * <h3>Why the length check first</h3>
 * <p>
 * It is a cheap O(1) early exit. Strings of different lengths can never have
 * the same letter counts, so we skip the sorting work entirely.
 * </p>
 *
 * <h3>Complexity</h3>
 * <p>Let {@code n} = length of the strings.</p>
 * <ul>
 *   <li><b>Time: O(n log n)</b>. Dominated by sorting.</li>
 *   <li><b>Space: O(n)</b>. The two {@code char[]} copies (sorting a string
 *       requires converting it to an array first).</li>
 * </ul>
 *
 * <h3>Faster alternative: frequency count</h3>
 * <p>
 * For lowercase letters only, use an {@code int[26]}. Add 1 for each character
 * of {@code s} and subtract 1 for each character of {@code t}. If every slot
 * ends at 0, it is an anagram. <b>O(n) time, O(1) space</b> (the array is a
 * fixed size of 26). For Unicode input, use a {@code HashMap<Character, Integer>}
 * instead.
 * </p>
 * <pre>
 *     int[] count = new int[26];
 *     for (int i = 0; i &lt; s.length(); i++) {
 *         count[s.charAt(i) - 'a']++;
 *         count[t.charAt(i) - 'a']--;
 *     }
 *     for (int c : count) if (c != 0) return false;
 *     return true;
 * </pre>
 *
 * <h3>Edge cases</h3>
 * <ul>
 *   <li>Both strings empty returns {@code true}.</li>
 *   <li>Different lengths return {@code false} immediately.</li>
 *   <li>Same letters but different counts, e.g. {@code "aab"} vs {@code "abb"},
 *       return {@code false}.</li>
 * </ul>
 */
public class ValidAnagram {

    public boolean validAnagram(String s, String t) {
        // Different lengths can never be anagrams, skip the work
        if (s.length() != t.length()) {
            return false;
        }

        // Sorting needs arrays, since Strings are immutable
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        // Anagrams become identical once their letters are sorted
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);
    }
}