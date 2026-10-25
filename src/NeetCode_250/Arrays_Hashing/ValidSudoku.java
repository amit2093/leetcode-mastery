package NeetCode_250.Arrays_Hashing;

/**
 * <h2>LeetCode 36: Valid Sudoku</h2>
 *
 * <h3>Problem</h3>
 * <p>
 * Given a 9x9 board where empty cells are {@code '.'}, decide whether the
 * <b>filled</b> cells are valid. The board is valid if:
 * </p>
 * <ul>
 *   <li>Each <b>row</b> has no repeated digit 1-9.</li>
 *   <li>Each <b>column</b> has no repeated digit 1-9.</li>
 *   <li>Each of the nine 3x3 <b>boxes</b> has no repeated digit 1-9.</li>
 * </ul>
 * <p>
 * Only validity is checked, not whether the puzzle is solvable.
 * </p>
 *
 * <h3>Idea: three independent checks</h3>
 * <p>
 * For every row, column and box, scan its 9 cells and keep a
 * {@code boolean[10]} called {@code found}, where {@code found[d]} means
 * "digit d was already seen in this group". If a digit shows up twice, the
 * board is invalid. Empty cells ({@code '.'}) are skipped.
 * </p>
 *
 * <h3>Converting a char to a digit</h3>
 * <p>
 * {@code c - '0'} turns the character {@code '7'} into the int {@code 7}, so
 * it can be used directly as an index into {@code found}. The array has size
 * 10 so that indices 1 to 9 map directly (index 0 is unused).
 * </p>
 *
 * <h3>Box numbering and the maths</h3>
 * <p>
 * Boxes are numbered 0 to 8, left to right, top to bottom:
 * </p>
 * <pre>
 *            cols 0-2   cols 3-5   cols 6-8
 *     rows 0-2 [ box 0 ] [ box 1 ] [ box 2 ]
 *     rows 3-5 [ box 3 ] [ box 4 ] [ box 5 ]
 *     rows 6-8 [ box 6 ] [ box 7 ] [ box 8 ]
 * </pre>
 * <p>
 * Top-left corner of a box:
 * </p>
 * <pre>
 *     startRow = 3 * (boxNumber / 3)    // integer division: 0,0,0,1,1,1,2,2,2 -> *3
 *     startCol = 3 * (boxNumber % 3)    // remainder:        0,1,2,0,1,2,0,1,2 -> *3
 *
 *     box 4 : startRow = 3 * (4 / 3) = 3,  startCol = 3 * (4 % 3) = 3
 *             scans rows 3..5 and cols 3..5
 *     box 7 : startRow = 3 * (7 / 3) = 6,  startCol = 3 * (7 % 3) = 3
 *             scans rows 6..8 and cols 3..5
 * </pre>
 *
 * <h3>Diagram</h3>
 * <p>Example: a row containing {@code 5 3 . . 7 . . . 5}</p>
 * <pre>
 *     5 -> found[5] = true
 *     3 -> found[3] = true
 *     . -> skip
 *     . -> skip
 *     7 -> found[7] = true
 *     . . . -> skip
 *     5 -> found[5] already true -> duplicate -> return false
 * </pre>
 *
 * <h3>Complexity</h3>
 * <ul>
 *   <li><b>Time: O(81)</b>, i.e. O(1) for a fixed 9x9 board. Each cell is
 *       visited three times (row pass, column pass, box pass).</li>
 *   <li><b>Space: O(1)</b>. A {@code boolean[10]} per check.</li>
 * </ul>
 *
 * <h3>Single-pass alternative</h3>
 * <p>
 * Use {@code boolean[9][10]} arrays {@code rows}, {@code cols} and
 * {@code boxes}, and visit each cell once. The box index of cell
 * {@code (r, c)} is {@code (r / 3) * 3 + (c / 3)}. Same complexity, but the
 * board is scanned once instead of three times.
 * </p>
 *
 * <h3>Edge cases</h3>
 * <ul>
 *   <li>Empty board (all {@code '.'}) is valid.</li>
 *   <li>A duplicate in a row, column or box makes the whole board invalid.</li>
 *   <li>The same digit in two <b>different</b> boxes is fine. This is why
 *       the box loop must start at the box's own corner and not at 0.</li>
 * </ul>
 */
public class ValidSudoku {

    // Row check: scan one row of 9 cells, return false on a repeated digit
    private boolean checkRows(char[] row) {
        boolean[] found = new boolean[10]; // found[d] = digit d already seen

        for (int i = 0; i < 9; i++) {
            if (row[i] == '.') {
                continue; // empty cell, nothing to check
            }

            int digit = row[i] - '0'; // '7' -> 7
            if (found[digit]) {
                return false; // seen before in this row
            }
            found[digit] = true;
        }
        return true;
    }

    // Column check: scan column j from top to bottom
    private boolean checkCols(char[][] board, int j) {
        boolean[] found = new boolean[10];

        for (int i = 0; i < 9; i++) {
            char c = board[i][j]; // row i, fixed column j
            if (c == '.') {
                continue;
            }

            int digit = c - '0';
            if (found[digit]) {
                return false;
            }
            found[digit] = true;
        }
        return true;
    }

    // Box check: scan the 3x3 block with the given number (0..8)
    private boolean checkBox(char[][] board, int boxNumber) {
        int i = 3 * (boxNumber / 3); // first row of the box
        int j = 3 * (boxNumber % 3); // first column of the box

        boolean[] found = new boolean[10];
        for (int a = i; a < i + 3; a++) {         // 3 rows of the box
            for (int b = j; b < j + 3; b++) {     // 3 columns of the box
                char c = board[a][b];
                if (c == '.') {
                    continue;
                }

                int digit = c - '0';
                if (found[digit]) {
                    return false;
                }
                found[digit] = true;
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {

        // All 9 rows
        for (int i = 0; i < 9; i++) {
            if (!checkRows(board[i])) return false;
        }
        // All 9 columns
        for (int j = 0; j < 9; j++) {
            if (!checkCols(board, j)) return false;
        }
        // All 9 boxes
        for (int boxNumber = 0; boxNumber < 9; boxNumber++) {
            if (!checkBox(board, boxNumber)) return false;
        }

        // No group had a repeated digit
        return true;
    }
}