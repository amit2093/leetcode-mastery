package NeetCode_250.array_and_hashing;

public class MajorityElement {

    public static void main(String[] args) {
        int i = majorityElement(new int[]{6,6,6,7,7});
        System.out.println(i);
    }

    public static int majorityElement(int[] nums) {
        int count = 0;
        int current = 0;

        for (int num : nums) {
            if (count == 0) {
                current = num;
            }
            count += (num == current) ? 1 : -1;
        }

        return current;
    }
}
