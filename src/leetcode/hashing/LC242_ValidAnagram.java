package leetcode.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: 242. Valid Anagram
 * Difficulty: Easy
 * Pattern: Hash Table / Frequency Counting
 * Time Complexity: O(n) - Two passes over strings of length n.
 * Space Complexity: O(k) - Where k is the number of unique characters (max 26 for lowercase English).
 *
 * Notes: Uses a frequency map to count occurrences. Increment for string 's'
 * and decrement for string 't'. If the map is empty at the end, they are anagrams.
 */
public class LC242_ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if(s.isEmpty()) return false;
        if(t.isEmpty()) return false;
        if(s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(int i = 0; i < t.length(); i++){
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
            if (map.get(t.charAt(i)) == 0) map.remove(t.charAt(i));
        }

        return map.isEmpty();
    }

}
