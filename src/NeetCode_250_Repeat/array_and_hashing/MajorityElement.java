package NeetCode_250_Repeat.array_and_hashing;

// https://neetcode.io/problems/majority-element/question?list=neetcode250

import java.util.HashMap;
import java.util.Map;

// Given an array nums of size n, return the majority element.
// The majority element is the element that appears more than ⌊n / 2⌋ times in the array. You may assume that the majority element always exists in the array.
// Example 1:
// Input: nums = [5,5,1,1,1,5,5]
// Output: 5
// Example 2:
// Input: nums = [2,2,2]
// Output: 2
public class MajorityElement {

    // Boyer-Moore Algorithm
    public int majorityElement(int[] nums) {
        int candidate = nums[0], count = 0;
        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    /**
     * CLAUDE:
     * Misra-Gries Algorithm.
     *
     * At k=2 this collapses exactly to Boyer-Moore — one counter, decrement-to-zero, replace.
     * Set k=3 and you have LC 229. Set k=100, and you have a heavy-hitters detector for a stream you can't store.
     *
     * This is the version with real-world teeth for you.
     * Detecting the top-N accounts by transaction volume in a Kafka stream,
     * or the merchants generating disproportionate alert volume in a KYC pipeline,
     * is Misra-Gries or its cousin Count-Min Sketch — you cannot hold a HashMap of every account ID,
     * and you don't need exact counts, you need the heavy hitters with a bounded error guarantee.
     * Same reason Flink and Spark ship approximate-frequency operators.
     *
     * This approach is the bridge from LeetCode to distributed systems,
     * and it's the kind of thing that comes up in a Stripe or Databricks system design round when someone asks
     * how you'd track top talkers without unbounded memory.
     *
     * What Misra-Gries gives you.
     * Fixed memory, chosen up front. Want the accounts exceeding 1% of traffic? k = 100, so you keep 99 counters. Not 200 million. Ninety-nine.
     * The guarantee is one-sided and precise: every element that truly appears more than n/k times is in your final set. There is no false negative. You may also carry some elements that don't qualify, and each stored count may undercount the true count by at most n/k. So you do one verification pass, or accept the approximation, depending on whether you can afford a second read.
     * That's the trade you're announcing to the interviewer: I gave up exactness and I got a hard memory bound, and here is precisely what I gave up.
     */
    public int theMisraGries(int[] nums) {
        Map<Integer, Integer> counters = new HashMap<>();
        int k = 2;                                       // > n/2 → k = 2 → 1 counter
        for (int num : nums) {
            counters.merge(num, 1, Integer::sum);
            if (counters.size() == k) {                  // too many candidates, decrement all
                counters.replaceAll((key, v) -> v - 1);
                counters.values().removeIf(v -> v == 0);
            }
        }
        return counters.keySet().iterator().next();      // verification pass omitted (guaranteed)
    }
}
