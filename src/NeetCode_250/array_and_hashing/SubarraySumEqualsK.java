package NeetCode_250.array_and_hashing;

import leetcode.common.LeetCodeUtils;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    public static void main(String[] args) {
        var solution = new SubarraySumEqualsK();

        LeetCodeUtils.runTest(
                4,
                () -> solution.optimized(new int[] {2, -1, 1, 2}, 2)
        );
    }

    // nums = 2,-1,1,2, k = 2
    public int bruteForce(int[] nums, int k) {
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    result++;
                }
            }
        }
        return result;
    }

        // nums = [2,-1,1,2], k = 2, ans = 4
        // this method is far more optimized version of prefixSum approach
        // I am not maintaining prefix sum array
        // But I am maintaining prefix sum array like element into "sum" variable
        // All I can say is, prefix sum is collapsed into this "sum" variable
        public int optimized(int[] nums, int k) {
            Map<Integer,Integer> map = new HashMap<>();
            map.put(0, 1); // already saw 0 sum, 1 time

            int result = 0;
            int sum = 0;

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if(map.containsKey(sum - k)){
                    /*
                     * for i = 0,
                     * sum = 2
                     * so sum - k i.e. 2-2 = 0 which my map already contains with value 1 (initial value)
                     * so result will be 1
                     * and this loop will continue...
                     */
                    result = result + map.get(sum - k);
                }
                /*
                 * for i = 1 or more
                 * sum = 1, (2 + (-1))
                 * keep this is into map and update this sum frequency...
                 */
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return result;
        }
}
