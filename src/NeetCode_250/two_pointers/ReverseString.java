package NeetCode_250.two_pointers;

public class ReverseString {
    public void reverseString(char[] s) {
        for(int i = 0; i < s.length / 2; i++){
            char firstChar = s[i];
            char lastChar = s[s.length - i - 1];
            s[s.length - i - 1] = firstChar;
            s[i] = lastChar;
        }
    }
}
