package NeetCode_250.two_pointers;

import leetcode.common.LeetCodeUtils;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        var sol  = new BestTimeToBuyAndSellStock();
        LeetCodeUtils.runTest(
                3,
                () -> sol.maxProfit(new int[] {1,4,2})
        );
    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price; // better buy point going forward
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}
