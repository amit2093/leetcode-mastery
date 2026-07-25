package NeetCode_250.two_pointers;

public class MergeStringsAlternately {

    /**
     * Input: word1 = "abc", word2 = "xyz"
     * Output: "axbycz"
     */
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int word1Length = word1.length();
        int word2Length = word2.length();
        if (word1Length == word2Length) {
            for (int i = 0, j = 0; i < word1.length() && j < word2.length(); i++, j++) {
                result.append(word1.charAt(i));
                result.append(word2.charAt(j));
            }
        } else if (word1Length < word2Length) {
            for (int i = 0, j = 0; i < word1Length && j < word2.length(); i++, j++) {
                result.append(word1.charAt(i));
                result.append(word2.charAt(j));
            }
            result.append(word2.substring(word1Length));
        } else {
            for (int i = 0, j = 0; i < word1Length && j < word2.length(); i++, j++) {
                result.append(word1.charAt(i));
                result.append(word2.charAt(j));
            }
            result.append(word1.substring(word2Length));
        }

        return result.toString();
    }

    public String mergeAlternately2(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        StringBuilder sb = new StringBuilder(n1 + n2);   // pre-size: no internal array resizing

        int min = Math.min(n1, n2);
        for (int i = 0; i < min; i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }

        // one of these will not append anything
        sb.append(word1, min, n1);
        sb.append(word2, min, n2);

        return sb.toString();
    }
}
