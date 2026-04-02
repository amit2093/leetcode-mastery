package leetcode.prefix_sum;

/**
 * Problem: 304. Range Sum Query 2D - Immutable
 * Difficulty: Medium
 * Pattern: 2D Prefix Sum (Inclusion-Exclusion Principle)
 * Time Complexity:
 * - Constructor: O(m * n) to build the 2D prefix sum matrix.
 * - sumRegion: O(1) per query.
 * Space Complexity: O(m * n) to store the prefix sum matrix.
 *
 * Notes: Uses a 2D cumulative sum array where prefixSum[i][j] represents
 * the sum of the rectangle from (0,0) to (i-1, j-1).
 */
public class LC304_RangeSumQuery2DImmutable_PrefixSum {
    static int[][] prefixSum = null;

    public LC304_RangeSumQuery2DImmutable_PrefixSum(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        prefixSum = new int[m + 1][n + 1];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                prefixSum[row + 1][col + 1] = matrix[row][col]
                                            + prefixSum[row + 1][col]
                                            + prefixSum[row][col + 1]
                                            - prefixSum[row][col];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum[row2 + 1] [col2 + 1]
             - prefixSum[row1]     [col2 + 1]
             - prefixSum[row2 + 1] [col1]
             + prefixSum[row1]     [col1];
    }
}
