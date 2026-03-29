package com.amit.common;

import java.util.*;

/**
 * Utility class for common LeetCode operations.
 * Helps in converting LeetCode input strings to Java Objects.
 */
public class LeetCodeUtils {

    /**
     * Converts a Level-Order string (like "[1,null,2,3]") to a TreeNode.
     */
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return null;

        String[] parts = input.split(",");
        String item = parts[0].trim();
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) break;

            item = parts[index++].trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) break;

            item = parts[index++].trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    /**
     * Pretty-print 2D arrays (useful for Matrix or DP problems)
     */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Measure execution time of a piece of code (for performance testing)
     *
     * Use it like this:
     * LeetCodeUtils.timeTaken(() -> {
     *     int result = classObject.lengthOfLongestSubstring(testInput);
     *     System.out.println("Result: " + result);
     * });
     */
    public static void timeTaken(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        System.out.printf("Execution Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }
}