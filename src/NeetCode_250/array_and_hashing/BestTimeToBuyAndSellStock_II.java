package NeetCode_250.array_and_hashing;

public class BestTimeToBuyAndSellStock_II {

    public static void main(String[] args) {
        maxProfit(new int[]{7,1,5,3,6,4});
    }

    // prices = 7,1,5,3,6,4
    public static int maxProfit(int[] prices) {
        int max = 0;
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = prices[0];
        if (prices.length == 1) {
            return min;
        }

        for (int i = 1; i < prices.length; i++) {
            int current = prices[i];
            if(current < min){
                min = current;
            }
            if(current - min > 0){
                max = max + (current - min);
                min = current;
            }
        }

        return max;
    }
}
