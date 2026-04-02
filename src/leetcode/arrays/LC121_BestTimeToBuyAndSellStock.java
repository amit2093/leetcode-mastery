package leetcode.arrays;

/**
 * Problem: 121. Best Time to Buy and Sell Stock
 * Difficulty: Easy
 * Pattern: Two Pointers / Greedy (Kadane's-like)
 * Time Complexity: O(n) - Single pass through the prices array.
 * Space Complexity: O(1) - Only constant extra space used.
 *
 * Notes: Tracks the minimum price seen so far (buy) and calculates
 * the potential profit at each step, updating the maximum profit found.
 */
public class LC121_BestTimeToBuyAndSellStock {

    // Kadane's Algorithm
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // simple: always buy at less
            // when price is < your buying price, update buy price
            // because we want to buy at lowest price.
            if (prices[i] < buy) {
                buy = prices[i];

                // this is profit booking
                // when day's price - buying price > profit till now,
                // book your profit (update profit)
            } else if (prices[i] - buy > profit) {
                profit = prices[i] - buy;
            }
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int max = 0;
        int j = 0;
        int len = prices.length;

        for (int i = 0; i < len; i++){
            int biggest = prices[i];
            j = i + 1;
            while (j < len) {
                if (biggest < prices[j]){
                    int tempMax = Math.max(max, prices[j] - biggest);
                    if (tempMax > max){
                        max = tempMax;
                    }
                }
                j++;
            }
        }
        return max;
    }


}
