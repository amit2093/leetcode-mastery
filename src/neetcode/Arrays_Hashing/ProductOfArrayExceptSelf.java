package neetcode.Arrays_Hashing;

/**
 * <h2>LeetCode 238: Product of Array Except Self</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given an integer array {@code nums}, return an array {@code answer} where
 * {@code answer[i]} is the product of all elements of {@code nums} except
 * {@code nums[i]}. You must not use division and must run in O(n) time.
 * </p>
 *
 * <h3>Idea: prefix and suffix products</h3>
 * <p>
 * The product of everything except {@code nums[i]} is:
 * </p>
 * <pre>
 *     (product of all elements LEFT of i) * (product of all elements RIGHT of i)
 * </pre>
 * <ul>
 *   <li>{@code prefix[i]} = product of {@code nums[0..i-1]}</li>
 *   <li>{@code sufix[i]}  = product of {@code nums[i+1..n-1]}</li>
 *   <li>{@code answer[i]} = {@code prefix[i] * sufix[i]}</li>
 * </ul>
 * <p>
 * The first element has nothing on its left and the last has nothing on its
 * right, so those boundary values are {@code 1} (the multiplicative identity).
 * </p>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code nums = [1, 2, 4, 6]}</p>
 * <pre>
 *     index  :   0    1    2    3
 *     nums   : [ 1,   2,   4,   6 ]
 *
 *     prefix : [ 1,   1,   2,   8 ]     prefix[i] = prefix[i-1] * nums[i-1]
 *                     1*1  1*2  2*4
 *
 *     sufix  : [ 48,  24,  6,   1 ]     sufix[i] = sufix[i+1] * nums[i+1]
 *                2*... 4*6  6*1
 *                48 = 2*4*6, 24 = 4*6
 *
 *     answer : [ 48,  24,  12,  8 ]     prefix[i] * sufix[i]
 *                1*48 1*24 2*6  8*1
 * </pre>
 *
 * <h3>Why {@code nums} can be overwritten at the end</h3>
 * <p>
 * By the last loop, everything needed is already stored in {@code prefix} and
 * {@code sufix}, so {@code nums} is free to reuse as the output array. This
 * saves allocating a third array. The downside is that it mutates the input.
 * </p>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time: O(n)</b>. Three passes over the array.</li>
 *   <li><b>Space: O(n)</b>. Two extra arrays, {@code prefix} and {@code sufix}.</li>
 * </ul>
 *
 * <h3>Optimization: O(1) extra space</h3>
 * <p>
 * Skip the {@code sufix} array. Store prefix products directly in the result
 * array, then sweep right to left keeping a running {@code suffix} variable
 * and multiply it in. The output array does not count as extra space.
 * </p>
 * <pre>
 *     int n = nums.length;
 *     int[] res = new int[n];
 *     res[0] = 1;
 *     for (int i = 1; i &lt; n; i++) res[i] = res[i - 1] * nums[i - 1];
 *
 *     int suffix = 1;
 *     for (int i = n - 1; i &gt;= 0; i--) {
 *         res[i] *= suffix;
 *         suffix *= nums[i];
 *     }
 *     return res;
 * </pre>
 *
 * <h3>Edge cases</h3>
 * <ul>
 *   <li>Array containing one {@code 0}: every answer is 0 except at the
 *       zero's index. This works automatically, no special handling.</li>
 *   <li>Array containing two or more zeros: all answers are 0.</li>
 *   <li>The problem guarantees {@code n >= 2}. For {@code n == 1} the answer
 *       would be {@code [1]} here.</li>
 * </ul>
 */
public class ProductOfArrayExceptSelf {

    /**
     * Input: nums = [1,2,4,6]
     * Output: [48,24,12,8]
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n]; // prefix[i] = product of everything left of i
        int[] sufix = new int[n];  // sufix[i]  = product of everything right of i

        // Nothing to the left of the first element, nothing to the right of the last
        prefix[0] = 1;
        sufix[n - 1] = 1;

        // Left to right: extend the running product by the previous element
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        // Right to left: extend the running product by the next element
        for (int i = n - 2; i >= 0; i--) {
            sufix[i] = sufix[i + 1] * nums[i + 1];
        }

        // Combine: (everything left of i) * (everything right of i)
        // Reusing nums as the output, since it is no longer needed
        for (int i = 0; i < n; i++) {
            nums[i] = prefix[i] * sufix[i];
        }

        return nums;
    }
}