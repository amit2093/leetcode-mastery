package leetcode.sliding_window;

import leetcode.arrays.common.LeetCodeUtils;

/**
 * Problem: 1248. Count Number of Nice Subarrays
 * Difficulty: Medium
 * Pattern: Sliding Window (At Most K - At Most K-1) OR Prefix Sum
 * Time Complexity: O(n)
 * Space Complexity: O(1) if using sliding window, O(n) if using Map
 *
 * Notes: "Flexible Sliding Window" or "Prefix Sum + Hash Map"
 */
public class LC1248_CountNumberOfNiceSubarrays_SlidingWindow {

    public static void main(String[] args) {
        var sol = new LC1248_CountNumberOfNiceSubarrays_SlidingWindow();
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;

        LeetCodeUtils.runTest(
                2,
                () -> sol.numberOfSubarrays(nums, k)
        );
    }

    public int numberOfSubarrays(int[] nums, int k) {
        // Professional Tip: Solving "Exactly K" is often easier by
        // calculating (At Most K) - (At Most K - 1)
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int left = 0, right = 0, count = 0, res = 0;
        while (right < nums.length) {
            if (nums[right] % 2 != 0) k--;

            while (k < 0) {
                if (nums[left] % 2 != 0) k++;
                left++;
            }
            // All subarrays ending at 'right' and starting between 'left' and 'right'
            res += right - left + 1;
            right++;
        }
        return res;
    }
}

/*

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

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
 */