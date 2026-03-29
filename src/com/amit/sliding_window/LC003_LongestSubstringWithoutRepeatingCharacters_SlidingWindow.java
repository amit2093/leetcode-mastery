package com.amit.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: 3. Longest Substring Without Repeating Characters
 * Difficulty: Medium
 * Pattern: Sliding Window (Flexible) + Hash Map
 * Time Complexity: O(n)
 * Space Complexity: O(min(m, n)) where m is the size of the alphabet
 */
public class LC003_LongestSubstringWithoutRepeatingCharacters_SlidingWindow {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int left = 0;

        // Map to store: Key = Character, Value = Next index after the char
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);

            // 1. Shrink: If we've seen this char, jump 'left' to the
            // position after its last occurrence
            if (map.containsKey(currentChar)) {
                left = Math.max(map.get(currentChar), left);
            }

            // 2. Update/Expand: Record the new position
            map.put(currentChar, right + 1);

            // 3. Result: Update global maximum
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
