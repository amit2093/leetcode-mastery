package leetcode.sliding_window;

import leetcode.common.LeetCodeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: 904. Fruit Into Baskets
 * Difficulty: Medium
 * Pattern: Sliding Window (At Most 2 Distinct Elements)
 * Time Complexity: O(n)
 * Space Complexity: O(1) - Because the map size is at most 3
 *
 * Notes: this is like find the longest subarray with at most 2 distinct elements
 */
public class LC904_FruitIntoBaskets_SlidingWindow {

    public static void main(String[] args) {
        var sol = new LC904_FruitIntoBaskets_SlidingWindow();
        int[] fruits = {1, 2, 3, 2, 2};

        LeetCodeUtils.runTest(
                4,
                () -> sol.totalFruit(fruits)
        );
    }

    public int totalFruit(int[] fruits) {
        int left = 0, maxFruits = 0;
        // Map to store: Key = Fruit Type, Value = Count in current window
        Map<Integer, Integer> baskets = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {
            // 1. Expand: Add fruit to basket
            int fruit = fruits[right];
            baskets.put(fruit, baskets.getOrDefault(fruit, 0) + 1);

            // 2. Shrink: If we have more than 2 types, remove from the left
            while (baskets.size() > 2) {
                int leftFruit = fruits[left];
                baskets.put(leftFruit, baskets.get(leftFruit) - 1);

                if (baskets.get(leftFruit) == 0) {
                    baskets.remove(leftFruit);
                }
                left++;
            }

            // 3. Update Result: Max length of the window
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }
}