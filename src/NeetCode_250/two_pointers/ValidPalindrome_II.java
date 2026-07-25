package NeetCode_250.two_pointers;

public class ValidPalindrome_II {

    /*
     * Input: s = "abbda"
     * Output: true
     * Just delete d and it will be palindrome
     */
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                // forced to delete s[l] or s[r] — try both, need only one to work
                return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
            }
            l++;
            r--;
        }
        return true;   // "at most one" deletion — zero is fine
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
