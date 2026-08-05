package NeetCode_250_Repeat.array_and_hashing;

import java.util.Arrays;

// https://neetcode.io/problems/is-anagram/question?list=neetcode250

// Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
// An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
// Input: s = "racecar", t = "carrace"
// Output: true
public class ValidAnagram {

    // sort the array and check if arrays are equal
    public boolean isAnagram(String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        return Arrays.equals(sArray,tArray);
    }

}
