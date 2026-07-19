package leetcode.sliding_window;

public class LC1343 {

    public static void main(String[] args) {

    }

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        double avg = Double.NEGATIVE_INFINITY;
        double sum = 0;
        int ans = 0;
        int x = 0;

        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        avg = sum / k;
        if(avg >= threshold){
            ans++;
        }
        for (int i = k; i < arr.length; i++) {
            sum -= arr[x];
            sum += arr[i];
            avg = sum / k;
            if(avg >= threshold){
                ans++;
            }
            x++;
        }
        return ans;
    }
}
