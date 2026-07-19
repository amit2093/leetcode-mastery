package NeetCode_250.array_and_hashing;

public class MajorityElement {

    public static void main(String[] args) {
        int i = majorityElement(new int[]{6,6,6,7,7});
        System.out.println(i);
    }

    public static int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
