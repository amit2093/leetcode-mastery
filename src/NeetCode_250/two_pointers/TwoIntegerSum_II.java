package NeetCode_250.two_pointers;

public class TwoIntegerSum_II {

    public int[] twoSum(int[] numbers, int target) {
        int last = numbers.length - 1;
        int start = 0;
        for (int num : numbers) {
            // as per question, array is already sorted.
            // means start + last if > target means reduce last
            // because last is the biggest number (sorted array)
            if(numbers[start] + numbers[last] > target){
                last--;
            } else if(numbers[start] + numbers[last] < target) {
                start++;
            } else if(numbers[start] + numbers[last] == target){
                return new int[]{start + 1, last + 1};
            }
        }
        return null;
    }
}
