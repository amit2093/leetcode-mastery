package NeetCode_250.array_and_hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElement_II {

    public List<Integer> majorityElement(int[] nums) {
        int givenFreq = nums.length / 3;
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        counts.forEach((k, v) -> {
            if (v > givenFreq) {
                result.add(k);
            }
        });
        return result;
    }
}
