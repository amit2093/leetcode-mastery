package neetcode.Arrays_Hashing;

/**
 * <h2>LeetCode 304: Range Sum Query 2D - Immutable</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given a 2D matrix, answer many queries {@code sumRegion(row1, col1, row2, col2)}
 * returning the sum of the rectangle with top-left {@code (row1, col1)} and
 * bottom-right {@code (row2, col2)}. Each query must take O(1) time.
 * </p>
 *
 * <h3>Idea: 2D prefix sum</h3>
 * <p>
 * {@code prefixSum[i][j]} = sum of the rectangle from {@code (0,0)} to
 * {@code (i,j)}. Build it once, then every query is just a few lookups.
 * </p>
 *
 * <h3>Building the table (inclusion-exclusion)</h3>
 * <pre>
 *     prefixSum[i][j] = matrix[i][j]
 *                     + prefixSum[i-1][j]      (top block)
 *                     + prefixSum[i][j-1]      (left block)
 *                     - prefixSum[i-1][j-1]    (overlap counted twice)
 * </pre>
 *
 * <h3>Answering a query</h3>
 * <pre>
 *     answer = total - top - left + topLeft
 *
 *     total   = prefixSum[row2][col2]
 *     top     = prefixSum[row1-1][col2]      (rows above the region)
 *     left    = prefixSum[row2][col1-1]      (columns left of the region)
 *     topLeft = prefixSum[row1-1][col1-1]    (removed twice, add back once)
 * </pre>
 *
 * <h3>Diagram</h3>
 * <p>Example matrix:</p>
 * <pre>
 *     matrix:            prefixSum:
 *     [ 3, 0, 1 ]        [  3,  3,  4 ]
 *     [ 5, 6, 3 ]        [  8, 14, 18 ]
 *     [ 1, 2, 0 ]        [  9, 17, 21 ]
 *
 *     prefixSum[1][1] = 6 + 3 (top) + 8 (left) - 3 (topLeft) = 14
 *
 *     Query sumRegion(1, 1, 2, 2)  (the bottom-right 2x2: 6,3,2,0)
 *         total   = prefixSum[2][2] = 21
 *         top     = prefixSum[0][2] = 4
 *         left    = prefixSum[2][0] = 9
 *         topLeft = prefixSum[0][0] = 3
 *         21 - 4 - 9 + 3 = 11      (6 + 3 + 2 + 0 = 11)
 * </pre>
 *
 * <h3>Why the boundary checks</h3>
 * <p>
 * When {@code i == 0} (or {@code j == 0}) there is no row above (or column to
 * the left), so that block contributes {@code 0}. The top check uses
 * {@code i}, the left check uses {@code j}, and topLeft needs both.
 * </p>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Constructor: O(m * n)</b> time, <b>O(m * n)</b> space.</li>
 *   <li><b>sumRegion: O(1)</b> time per query.</li>
 * </ul>
 */
public class RangeSumQuery2D_Immutable {

    int[][] prefixSum;

    public RangeSumQuery2D_Immutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        prefixSum = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Block above: depends on the ROW
                int top = (i > 0) ? prefixSum[i - 1][j] : 0;
                // Block to the left: depends on the COLUMN (was i > 0, a bug)
                int left = (j > 0) ? prefixSum[i][j - 1] : 0;
                // Overlap of top and left, counted twice, so subtract once
                int topLeft = (i > 0 && j > 0) ? prefixSum[i - 1][j - 1] : 0;

                prefixSum[i][j] = matrix[i][j] + top + left - topLeft;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // Everything from (0,0) to the bottom-right corner of the region
        int total = prefixSum[row2][col2];
        // Cut off the rows above the region
        int top = (row1 > 0) ? prefixSum[row1 - 1][col2] : 0;
        // Cut off the columns left of the region
        int left = (col1 > 0) ? prefixSum[row2][col1 - 1] : 0;
        // That corner was cut twice, so add it back once
        int topLeft = (row1 > 0 && col1 > 0) ? prefixSum[row1 - 1][col1 - 1] : 0;

        return total - top - left + topLeft;
    }
}