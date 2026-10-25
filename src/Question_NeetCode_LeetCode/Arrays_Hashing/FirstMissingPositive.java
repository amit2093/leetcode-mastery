package Question_NeetCode_LeetCode.Arrays_Hashing;

/**
 * <h2>LeetCode 41: First Missing Positive</h2>
 *
 * <h3>Key observation</h3>
 * <p>
 * For an array of size {@code n}, the answer is always in the range
 * {@code 1..n+1}. If all of {@code 1..n} are present, the answer is
 * {@code n+1}. Otherwise one of {@code 1..n} is missing. So negatives, zeros
 * and values greater than {@code n} can never be the answer and are
 * irrelevant.
 * </p>
 *
 * <h3>Idea: use the array itself as a "seen" table</h3>
 * <p>
 * We can't use a HashMap (O(1) space required), so we use the
 * <b>sign</b> of {@code nums[v-1]} as a flag meaning "value v was seen".
 * </p>
 * <ul>
 *   <li>Negative at index {@code v-1} means {@code v} exists in the array</li>
 *   <li>Positive at index {@code v-1} means {@code v} was never seen</li>
 * </ul>
 *
 * <h3>Three steps</h3>
 * <ol>
 *   <li><b>Clean:</b> replace every useless value ({@code <= 0} or
 *       {@code > n}) with {@code n+1}. Now every element is positive, so
 *       the sign can safely be used as a flag.</li>
 *   <li><b>Mark:</b> for each value {@code v} in {@code 1..n}, flip the sign
 *       of {@code nums[v-1]} to negative.</li>
 *   <li><b>Scan:</b> the first index that is still positive gives the answer
 *       {@code i+1}. If none, the answer is {@code n+1}.</li>
 * </ol>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code nums = [3, 4, -1, 1]}, so {@code n = 4}</p>
 *
 * <p><b>Step 1: Clean</b> (replace {@code -1} with {@code n+1 = 5})</p>
 * <pre>
 *     index:   0   1   2   3
 *     nums : [ 3,  4,  5,  1 ]
 * </pre>
 *
 * <p><b>Step 2: Mark</b> (value v flips the sign at index v-1)</p>
 * <pre>
 *     read 3 &rarr; flip index 2   [ 3,  4, -5,  1 ]
 *     read 4 &rarr; flip index 3   [ 3,  4, -5, -1 ]
 *     read 5 &rarr; out of range, skip
 *     read 1 (abs of -1) &rarr; flip index 0   [-3,  4, -5, -1 ]
 * </pre>
 *
 * <p><b>Step 3: Scan</b> (first positive index)</p>
 * <pre>
 *     index:   0   1   2   3
 *     nums : [-3,  4, -5, -1 ]
 *                  ^
 *             index 1 is positive &rarr; value 2 was never seen
 *
 *     Answer = 1 + 1 = 2
 * </pre>
 *
 * <h3>Why {@code Math.abs} is needed</h3>
 * <p>
 * By the time we read {@code nums[i]}, it may already have been flipped
 * negative by an earlier step. {@code Math.abs} recovers the original value.
 * </p>
 *
 * <h3>Why the {@code nums[num - 1] > 0} check is needed</h3>
 * <p>
 * Duplicates. Without the check, seeing the same value twice would flip the
 * sign back to positive and wrongly say the value is missing.
 * </p>
 *
 * <h3>Why the sentinel is {@code n+1}</h3>
 * <p>
 * It must be positive (so it doesn't look like a "seen" flag) and out of
 * range (so Step 2 skips it). Adding 1 to the bad value does not guarantee
 * either, which is why a fixed {@code n+1} is used.
 * </p>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time: O(n)</b>. Three separate passes over the array.</li>
 *   <li><b>Space: O(1)</b>. The input array is modified in place.</li>
 * </ul>
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int length = nums.length;

        // Step 1: CLEAN
        // Replace anything that can't be the answer (<= 0 or > n) with n+1,
        // which is positive and out of range. After this, all values are positive.
        for (int i = 0; i < length; i++) {
            if (nums[i] <= 0 || nums[i] > length) {
                nums[i] = length + 1;
            }
        }

        // Step 2: MARK
        // For each value v in 1..n, make nums[v-1] negative to say "v exists".
        for (int i = 0; i < length; i++) {
            int num = Math.abs(nums[i]); // may already be flipped, so take abs

            if (num > length) {
                continue; // out of range (the n+1 sentinel), ignore it
            }

            // flip only if still positive, so duplicates don't undo the flag
            if (nums[num - 1] > 0) {
                nums[num - 1] = -nums[num - 1];
            }
        }

        // Step 3: SCAN
        // The first index that is still positive means value i+1 was never seen.
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // Every value 1..n was present, so the answer is n+1.
        return length + 1;
    }
}