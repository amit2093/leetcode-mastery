package NeetCode_250.Arrays_Hashing;

/**
 * <h2>LeetCode 27: Remove Element</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given an integer array {@code nums} and a value {@code val}, remove all
 * occurrences of {@code val} <b>in place</b>. Return {@code k}, the number of
 * elements that are not equal to {@code val}. The first {@code k} slots of
 * {@code nums} must hold those elements (any order). What is stored after
 * index {@code k} does not matter.
 * </p>
 *
 * <h3>Idea: two pointers (reader and writer)</h3>
 * <ul>
 *   <li>{@code i} is the <b>reader</b>: scans every element once.</li>
 *   <li>{@code j} is the <b>writer</b>: the next slot where a kept element
 *       should be placed.</li>
 * </ul>
 * <p>
 * If {@code nums[i]} is not {@code val}, copy it to {@code nums[j]} and move
 * {@code j} forward. If it equals {@code val}, skip it. At the end,
 * {@code j} is the count of kept elements, and {@code nums[0..j-1]} holds them.
 * </p>
 *
 * <h3>Diagram</h3>
 * <p>Example: {@code nums = [3, 2, 2, 3]}, {@code val = 3}</p>
 * <pre>
 *     i = 0 : nums[0] = 3  == val   skip              j = 0   [3, 2, 2, 3]
 *     i = 1 : nums[1] = 2  != val   nums[0] = 2, j++  j = 1   [2, 2, 2, 3]
 *     i = 2 : nums[2] = 2  != val   nums[1] = 2, j++  j = 2   [2, 2, 2, 3]
 *     i = 3 : nums[3] = 3  == val   skip              j = 2   [2, 2, 2, 3]
 *
 *     return 2, and the first 2 slots are [2, 2]
 * </pre>
 *
 * <h3>Why {@code j <= i} always</h3>
 * <p>
 * The writer never gets ahead of the reader, so a write never overwrites an
 * element that has not been read yet. That is what makes in-place safe.
 * </p>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time: O(n)</b>. One pass.</li>
 *   <li><b>Space: O(1)</b>. Only two index variables.</li>
 * </ul>
 *
 * <h3>Edge cases</h3>
 * <ul>
 *   <li>Empty array returns 0.</li>
 *   <li>No element equals {@code val}: nothing is removed, returns {@code n}.</li>
 *   <li>Every element equals {@code val}: returns 0.</li>
 * </ul>
 *
 * <h3>Alternative: swap with the end</h3>
 * <p>
 * When {@code val} is rare or the order does not matter, overwrite a matching
 * element with the last element and shrink the length. That does fewer
 * writes, but the two-pointer version above is simpler to remember.
 * </p>
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int i = 0; // reader: looks at every element
        int j = 0; // writer: next position for a kept element

        while (i < nums.length) {
            if (nums[i] != val) {
                nums[j] = nums[i]; // keep it: copy to the front part
                j++;
            }
            // if nums[i] == val, skip it (do not advance the writer)
            i++;
        }

        // j = number of kept elements, stored in nums[0..j-1]
        return j;
    }
}