package NeetCode_250.Arrays_Hashing;

import java.util.*;

/**
 * <h2>LeetCode 347: Top K Frequent Elements</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given an integer array {@code nums} and an integer {@code k}, return the
 * {@code k} most frequent elements. The answer is guaranteed to be unique,
 * and the order of the result does not matter.
 * </p>
 *
 * <h3>Idea (count, then sort by frequency)</h3>
 * <ol>
 *   <li><b>Count:</b> build a {@code HashMap} of number to frequency.</li>
 *   <li><b>Sort:</b> put the distinct numbers in a list and sort it by
 *       frequency, highest first.</li>
 *   <li><b>Take:</b> the first {@code k} entries of the sorted list are the
 *       answer.</li>
 * </ol>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code nums = [1, 2, 2, 3, 3, 3]}, {@code k = 2}</p>
 * <pre>
 *     Step 1: count
 *         map = { 1=1, 2=2, 3=3 }
 *
 *     Step 2: list of distinct keys, sorted by frequency (high to low)
 *         before : [1, 2, 3]
 *         after  : [3, 2, 1]        (freq 3, 2, 1)
 *
 *     Step 3: take first k = 2
 *         res = [3, 2]
 * </pre>
 *
 * <h3>Why {@code map.get(b) - map.get(a)}</h3>
 * <p>
 * The comparator receives two numbers {@code a} and {@code b}. Subtracting
 * {@code a}'s frequency from {@code b}'s gives a positive result when
 * {@code b} is more frequent, which puts {@code b} first. That produces a
 * descending order. Subtraction is safe here because frequencies are at most
 * the array length, so there is no int overflow.
 * </p>
 *
 * <h3>Complexity</h3>
 * <p>Let {@code n} = array length, {@code d} = number of distinct values.</p>
 * <ul>
 *   <li><b>Time: O(n + d log d)</b>, which is O(n log n) in the worst case
 *       when all values are distinct.</li>
 *   <li><b>Space: O(d)</b>. The map and the list.</li>
 * </ul>
 *
 * <h3>Faster alternatives</h3>
 * <ul>
 *   <li><b>Min-heap of size k:</b> keep only the k best while scanning the
 *       map. <b>O(n log k)</b> time. Good when k is much smaller than d.</li>
 *   <li><b>Bucket sort:</b> make {@code buckets[f]} a list of numbers that
 *       appear exactly {@code f} times (frequency is at most {@code n}), then
 *       read buckets from high frequency to low until you have k numbers.
 *       <b>O(n)</b> time. This is the follow-up interviewers usually want.</li>
 * </ul>
 *
 * <h3>Edge cases</h3>
 * <ul>
 *   <li>{@code k} equals the number of distinct values: returns all of them.</li>
 *   <li>Single element array: returns that element.</li>
 *   <li>Negative numbers are fine, they are just map keys.</li>
 * </ul>
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        TopKFrequentElements sol = new TopKFrequentElements();
        int[] nums1 = {1, 2, 2, 3, 3, 3};
        System.out.println(Arrays.toString(sol.topKFrequent(nums1, 2))); // [3, 2]
    }

    // O(n log n)
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];

        // Step 1: count how many times each number appears
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: list the distinct numbers and sort by frequency, highest first
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));

        // Step 3: the first k numbers are the k most frequent
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}