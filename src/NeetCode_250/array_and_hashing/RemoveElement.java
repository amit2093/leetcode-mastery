package NeetCode_250.array_and_hashing;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[ans++] = nums[i];
            }
        }
        return ans;
    }
}
