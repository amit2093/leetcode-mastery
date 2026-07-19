package neetcode_150.array_and_hashing;

import leetcode.common.LeetCodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {

    public static void main(String[] args) {
        var solution = new TopKFrequentElements();
        int[] nums = {1,2,2,3,3,3};
        int k = 2;
        int[] output = {3,2};

        LeetCodeUtils.runTest(
                output,
                () -> solution.topKFrequent(nums, k)
        );
    }

    public int[] topKFrequent(int[] nums, int k) {
        List<Integer>[] buckets = new List[nums.length + 1];

        // find frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // create buckets of each frequency (bucket will contain element of that frequency)
        for (int element : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(element);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(element);
        }

        // find top k elements
        int[] res = new int[k];
        int counter = 0;
        for (int position = buckets.length - 1; position >= 0 && counter < k; position--) {
            if (buckets[position] != null) {
                for (int element : buckets[position]) {
                    res[counter++] = element;
                }
            }
        }

        return res;
    }
}
