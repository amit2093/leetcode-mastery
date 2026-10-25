package neetcode.Arrays_Hashing;

import java.util.*;

/**
 * <h2>LeetCode 128: Longest Consecutive Sequence</h2>
 *
 * <h3>Idea</h3>
 * <p>
 * Put every number in a {@code HashMap} so "does X exist?" is an O(1) lookup.
 * For each number, expand outward (right: +1, left: -1) while neighbours
 * exist, and count how long the run is.
 * </p>
 *
 * <p>{@code HashMap<Integer, Boolean> exploredMap}</p>
 * <ul>
 *   <li><b>key</b>: a number present in the array</li>
 *   <li><b>value</b>: has this number already been absorbed into a counted run?
 *       {@code FALSE} = not counted yet, {@code TRUE} = already counted</li>
 * </ul>
 *
 * <p>
 * Marking numbers {@code TRUE} is what keeps this O(n): each number is
 * absorbed by an expansion at most once, so we never re-walk a run.
 * </p>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code nums = [100, 4, 200, 1, 3, 2]}</p>
 *
 * <p>Initial map (all {@code FALSE}):</p>
 * <pre>
 *     100:F   4:F   200:F   1:F   3:F   2:F
 * </pre>
 *
 * <p>Number line view:</p>
 * <pre>
 *      1    2    3    4              100           200
 *      o----o----o----o               o             o
 * </pre>
 *
 * <p><b>Step 1:</b> {@code num = 100}</p>
 * <pre>
 *     right: 101? not in map &rarr; stop
 *     left :  99? not in map &rarr; stop
 *     length = 1                    longest = 1
 * </pre>
 *
 * <p><b>Step 2:</b> {@code num = 4}</p>
 * <pre>
 *     right: 5? not in map &rarr; stop
 *     left : 3? in map, F &rarr; mark T, length 2
 *            2? in map, F &rarr; mark T, length 3
 *            1? in map, F &rarr; mark T, length 4
 *            0? not in map &rarr; stop
 *     length = 4                    longest = 4
 *
 *      1    2    3    4
 *      T    T    T    F   (4 itself stays F)
 *      &lt;----- expanded left from 4
 * </pre>
 *
 * <p><b>Step 3:</b> {@code num = 200} &rarr; length 1, longest = 4</p>
 *
 * <p><b>Step 4:</b> {@code num = 1}</p>
 * <pre>
 *     right: 2 is already T &rarr; stop immediately
 *     left : 0 not in map  &rarr; stop
 *     length = 1                    longest = 4
 * </pre>
 * <p>(the run was already counted in Step 2, so no re-walking)</p>
 *
 * <p><b>Step 5:</b> {@code num = 3}</p>
 * <pre>
 *     right: 4 is still F &rarr; mark T, length 2
 *     left : 2 is already T &rarr; stop
 *     length = 2                    longest = 4
 * </pre>
 *
 * <p><b>Step 6:</b> {@code num = 2} &rarr; neighbours 1 and 3 already T, length 1</p>
 *
 * <p><b>Answer = 4</b> (1, 2, 3, 4)</p>
 *
 * <h3>Why expanding in both directions works</h3>
 * <p>
 * The first number we pick from a run may be in the middle, e.g. 3 in
 * {@code [1,2,3,4,5]}. Going only right would miss 1 and 2, so we go right
 * <b>and</b> left. Since nothing in that run is marked yet, the first number
 * visited from a run always measures the <b>full</b> run. Later numbers from
 * the same run see {@code TRUE} neighbours and only produce small lengths,
 * which never beat the max.
 * </p>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time: O(n)</b>. Building the map is O(n), the outer loop is O(n),
 *       and each number is marked {@code TRUE} at most once across all while
 *       loops, so total expansion work is O(n).</li>
 *   <li><b>Space: O(n)</b>. The HashMap.</li>
 * </ul>
 *
 * <h3>Notes / edge cases</h3>
 * <ul>
 *   <li>Empty array returns 0 (the loop never runs).</li>
 *   <li>Duplicates are fine: the map collapses them, and a repeated visit
 *       just finds {@code TRUE} neighbours and yields a short length.</li>
 *   <li>The starting {@code num} itself is never marked {@code TRUE}. That is
 *       okay, because a later expansion from a neighbour may absorb it, but
 *       only inside a run no longer than the true run, so the max stays
 *       correct.</li>
 *   <li>Overflow: {@code num + 1} at {@code Integer.MAX_VALUE} wraps to
 *       {@code Integer.MIN_VALUE} (same for {@code num - 1} at MIN_VALUE).
 *       Only matters if both extremes are in the input.</li>
 * </ul>
 *
 * <h3>Alternative: "start of sequence" approach</h3>
 * <p>
 * Use a {@code HashSet}. Only start counting from {@code num} where
 * {@code num - 1} is <b>not</b> in the set, then walk right. Same O(n), no
 * marking needed, and usually the cleaner version to remember.
 * </p>
 *
 * @param nums the input array (may contain duplicates, unsorted)
 * @return the length of the longest run of consecutive integers
 */

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int longestSequence = 0;

        // Step 1: store every number, all marked "not explored yet"
        Map<Integer, Boolean> exploredMap = new HashMap<>();
        for (int num : nums) {
            exploredMap.put(num, Boolean.FALSE);
        }

        // Step 2: from each number, expand outward and count the run
        for (int num : nums) {
            int currentLength = 1; // the number itself

            // expand right: num+1, num+2, ...
            int nextNumber = num + 1;
            while (exploredMap.containsKey(nextNumber) && !exploredMap.get(nextNumber)) {
                currentLength++;
                exploredMap.put(nextNumber, Boolean.TRUE); // absorbed, never walk it again
                nextNumber++;
            }

            // expand left: num-1, num-2, ...
            int previousNum = num - 1;
            while (exploredMap.containsKey(previousNum) && !exploredMap.get(previousNum)) {
                currentLength++;
                exploredMap.put(previousNum, Boolean.TRUE);
                previousNum--;
            }

            longestSequence = Math.max(longestSequence, currentLength);
        }
        return longestSequence;
    }
}
