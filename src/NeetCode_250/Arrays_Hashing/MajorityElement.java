package NeetCode_250.Arrays_Hashing;

import java.util.*;

/**
 * <h2>LeetCode 169: Majority Element</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given an array {@code nums} of size {@code n}, return the majority element:
 * the value that appears <b>more than {@code n / 2}</b> times. The problem
 * guarantees that a majority element always exists.
 * </p>
 *
 * <h3>Idea (HashMap frequency count)</h3>
 * <p>
 * Walk through the array and keep a count of how many times each number has
 * appeared. The moment any number's count goes above {@code n / 2}, it is the
 * majority element, so we return it immediately.
 * </p>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code nums = [2, 2, 1, 1, 2]}, so {@code n = 5} and the
 * threshold is {@code n / 2 = 2} (we need a count greater than 2)</p>
 * <pre>
 *     num = 2 : map = {2=1}          1 &gt; 2? no
 *     num = 2 : map = {2=2}          2 &gt; 2? no
 *     num = 1 : map = {2=2, 1=1}     1 &gt; 2? no
 *     num = 1 : map = {2=2, 1=2}     2 &gt; 2? no
 *     num = 2 : map = {2=3, 1=2}     3 &gt; 2? yes &rarr; return 2
 * </pre>
 *
 * <h3>Why {@code > nums.length / 2}</h3>
 * <p>
 * "Majority" means strictly more than half. Integer division makes
 * {@code 5 / 2 = 2}, so a count of 3 passes and a count of 2 does not.
 * Only one value can ever exceed half the array.
 * </p>
 *
 * <h3>Why {@code return -1} at the end</h3>
 * <p>
 * It is never reached for valid input, because a majority element is
 * guaranteed. It only exists because Java requires a return statement.
 * </p>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time: O(n)</b>. One pass, O(1) per HashMap operation.</li>
 *   <li><b>Space: O(n)</b>. The map can hold up to about n/2 distinct values
 *       in the worst case.</li>
 * </ul>
 *
 * <h3>Better alternative: Boyer-Moore Voting (O(1) space)</h3>
 * <p>
 * Keep one {@code candidate} and a {@code count}. For each number: if
 * {@code count == 0}, make it the new candidate. Then add 1 to {@code count}
 * if the number equals the candidate, otherwise subtract 1. Every non-majority
 * element cancels out one majority element, and because the majority has more
 * than half the votes, it survives. Time stays O(n), space drops to O(1).
 * </p>
 * <pre>
 *     int candidate = 0, count = 0;
 *     for (int num : nums) {
 *         if (count == 0) candidate = num;
 *         count += (num == candidate) ? 1 : -1;
 *     }
 *     return candidate;
 * </pre>
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        // key: number, value: how many times it has appeared so far
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            // Increase this number's count (start from 0 if first time)
            map.put(num, map.getOrDefault(num, 0) + 1);

            // Strictly more than half means this is the majority element
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }

        // Unreachable for valid input (a majority element always exists)
        return -1;
    }
}