package NeetCode_250.Arrays_Hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <h2>LeetCode 217: Contains Duplicate</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given an integer array {@code nums}, return {@code true} if any value
 * appears at least twice, and {@code false} if every element is distinct.
 * </p>
 *
 * <h3>Idea (HashSet)</h3>
 * <p>
 * Walk through the array once and remember every number seen so far in a
 * {@code HashSet}. Before adding a number, ask the set "have I seen this
 * already?". A {@code HashSet} answers in O(1), so the first repeat is
 * caught immediately.
 * </p>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code nums = [1, 2, 3, 3]}</p>
 * <pre>
 *     num = 1 : set = {}         1 not in set &rarr; add   set = {1}
 *     num = 2 : set = {1}        2 not in set &rarr; add   set = {1, 2}
 *     num = 3 : set = {1, 2}     3 not in set &rarr; add   set = {1, 2, 3}
 *     num = 3 : set = {1, 2, 3}  3 IS in set  &rarr; return true
 * </pre>
 *
 * <h3>Approaches compared</h3>
 * <ul>
 *   <li><b>HashSet (early exit):</b> O(n) time, O(n) space. Stops at the
 *       first duplicate, so it is often faster in practice.</li>
 *   <li><b>Streams {@code distinct().count()}:</b> O(n) time, O(n) space.
 *       Short to write, but always processes the whole array (no early
 *       exit).</li>
 *   <li><b>Sorting:</b> sort, then check neighbours. O(n log n) time,
 *       O(1) extra space if sorting in place. Use it when memory matters.</li>
 * </ul>
 *
 * <h3>Edge cases</h3>
 * <ul>
 *   <li>Single element &rarr; {@code false} (nothing to repeat).</li>
 *   <li>Whole array identical &rarr; {@code true} at the second element.</li>
 * </ul>
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3};

        System.out.println(check(nums));        // true
        System.out.println(usingStreams(nums)); // true
    }

    /**
     * HashSet approach: remember seen numbers, return true on the first repeat.
     */
    private static boolean check(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true; // seen before, so it is a duplicate
            }
            set.add(num);    // first time seeing it, remember it
        }
        return false; // finished without a repeat, all distinct
    }

    /**
     * Streams approach: if the count of distinct values is smaller than the
     * array length, something was repeated.
     */
    private static boolean usingStreams(int[] nums) {
        return Arrays.stream(nums)
                .distinct()
                .count() != nums.length;
    }
}