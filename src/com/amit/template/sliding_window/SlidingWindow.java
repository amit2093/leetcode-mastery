package com.amit.template.sliding_window;

public class SlidingWindow {

    /**
     * Generic Sliding Window Template
     * 1. Initialize 'left' and 'right' pointers at 0.
     * 2. Expand 'right' to include new elements.
     * 3. While (condition is violated): shrink from 'left'.
     * 4. Update the result (max/min/count).
     */
    public int template(String s) {
        int left = 0, right = 0, result = 0;
        // Map or Counter for frequencies/conditions
        while (right < s.length()) {
            // 1. Expand
            // 2. Shrink logic
            // 3. Update result
            right++;
        }
        return result;
    }
}
