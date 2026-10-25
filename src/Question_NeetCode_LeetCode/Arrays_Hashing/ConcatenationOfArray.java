package Question_NeetCode_LeetCode.Arrays_Hashing;

import java.util.Arrays;

/**
 * <h2>LeetCode 1929: Concatenation of Array</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given an array {@code nums} of length {@code n}, return an array
 * {@code ans} of length {@code 2n} where {@code ans} is {@code nums}
 * followed by {@code nums} again.
 * </p>
 *
 * <h3>Idea</h3>
 * <p>
 * Create the result array of size {@code 2n}. For every index {@code i},
 * write {@code nums[i]} to <b>two</b> places in a single pass:
 * </p>
 * <ul>
 *   <li>index {@code i} (first copy)</li>
 *   <li>index {@code i + n} (second copy)</li>
 * </ul>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code nums = [1, 4, 1, 2]}, so {@code n = 4}</p>
 * <pre>
 *     nums   :  [ 1, 4, 1, 2 ]
 *     index  :    0  1  2  3
 *
 *     output :  [ _, _, _, _, _, _, _, _ ]
 *     index  :    0  1  2  3  4  5  6  7
 *                 |-- first copy --|-- second copy --|
 *
 *     i = 0 : output[0] = 1,  output[4] = 1
 *     i = 1 : output[1] = 4,  output[5] = 4
 *     i = 2 : output[2] = 1,  output[6] = 1
 *     i = 3 : output[3] = 2,  output[7] = 2
 *
 *     output :  [ 1, 4, 1, 2, 1, 4, 1, 2 ]
 * </pre>
 *
 * <h3>Why {@code i + nums.length}</h3>
 * <p>
 * The second copy starts right after the first copy ends, which is at index
 * {@code n}. So element {@code i} of the second copy lives at {@code n + i}.
 * </p>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time: O(n)</b>. One pass over {@code nums}.</li>
 *   <li><b>Space: O(n)</b>. The output array of size {@code 2n} (required
 *       by the problem, so it doesn't count as extra space).</li>
 * </ul>
 */
public class ConcatenationOfArray {

    public static int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] output = new int[2 * n]; // room for two copies

        for (int i = 0; i < n; i++) {
            output[i] = nums[i];     // first copy
            output[i + n] = nums[i]; // second copy, shifted by n
        }
        return output;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 1, 2};
        int[] output = getConcatenation(nums);

        System.out.println(Arrays.toString(output)); // [1, 4, 1, 2, 1, 4, 1, 2]
    }
}