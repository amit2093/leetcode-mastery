package NeetCode_250.two_pointers;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if (s.isEmpty()) return true;

        boolean res = true;

        int j = s.length() - 1;

        for (int i = 0; i < s.length() / 2; i++) {
            if(s.charAt(i) != s.charAt(j)){
                res = false;
                break;
            }
            j--;
        }
        return res;
    }
}
