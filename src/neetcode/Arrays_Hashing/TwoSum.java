package neetcode.Arrays_Hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h2>LeetCode 1: Two Sum</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given an integer array {@code nums} and an integer {@code target}, return
 * the indices of the two numbers that add up to {@code target}. Exactly one
 * solution exists, and the same element cannot be used twice.
 * </p>
 *
 * <h3>Idea (one-pass HashMap)</h3>
 * <p>
 * For each number {@code x}, the partner we need is {@code target - x}.
 * Keep a map of every number seen so far to its index. At each step:
 * </p>
 * <ol>
 *   <li>Check if {@code target - x} is already in the map. If yes, we found
 *       the pair: return its stored index and the current index.</li>
 *   <li>If not, store {@code x -> i} in the map and move on.</li>
 * </ol>
 * <p>
 * The map lookup is O(1), which replaces the inner loop of the brute-force
 * O(n^2) solution.
 * </p>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code nums = [2, 7, 11, 15]}, {@code target = 9}</p>
 * <pre>
 *     i = 0 : x = 2   need 9 - 2 = 7   map = {}          7 not found &rarr; store   map = {2=0}
 *     i = 1 : x = 7   need 9 - 7 = 2   map = {2=0}       2 FOUND at index 0
 *
 *     return [0, 1]
 * </pre>
 *
 * <h3>Why check before inserting</h3>
 * <p>
 * Looking up the partner <b>before</b> storing the current number guarantees
 * we never pair an element with itself. For {@code nums = [3, 2, 4]} and
 * {@code target = 6}, the 3 does not match itself, because it is not in the
 * map yet when we look for {@code 6 - 3 = 3}.
 * </p>
 *
 * <h3>Duplicates</h3>
 * <p>
 * {@code nums = [3, 3]}, {@code target = 6}: at i = 0 the map is empty, so 3
 * is stored. At i = 1 the partner 3 is found at index 0, so the answer is
 * {@code [0, 1]}. Overwriting a duplicate key in the map is not a problem,
 * because the match is always found when the second copy is processed.
 * </p>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time: O(n)</b>. One pass, O(1) per map operation.</li>
 *   <li><b>Space: O(n)</b>. The map can hold up to n entries.</li>
 * </ul>
 *
 * <h3>Edge cases</h3>
 * <ul>
 *   <li>Negative numbers and zero work the same way.</li>
 *   <li>The final {@code return res} (the default {@code [0, 0]}) is never
 *       reached for valid input, because a solution is guaranteed.</li>
 * </ul>
 */
public class TwoSum {

    public static void main(String[] args) {
        TwoSum sol = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(sol.twoSum(nums, 9))); // [0, 1]
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];

        // key: number seen so far, value: its index
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i]; // the partner that completes the sum

            // Partner seen earlier? Then we have the answer
            if (map.containsKey(need)) {
                res[0] = map.get(need); // earlier index
                res[1] = i;             // current index
                return res;
            }

            // Not found yet: remember this number for future elements
            map.put(nums[i], i);
        }

        // Unreachable for valid input (a solution is guaranteed)
        return res;
    }
}