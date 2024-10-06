public class bestTimeToBuyAndSellStocks {


// Buy and Sell Crypto/stocks

// You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.

// You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.

// Return the maximum profit you can achieve. You may choose to not make any transactions, in which case the profit would be 0.

// Example 1:
// Input: prices = [10,1,5,6,7,1]
// Output: 6
// Explanation: Buy prices[1] and sell prices[4], profit = 7 - 1 = 6.

// Example 2:
// Input: prices = [10,8,7,5,2]
// Output: 0
// Explanation: No profitable transactions can be made, thus the max profit is 0.

    public static int maxProfit(int[] prices) {
        
        // two pointers for sliding window
        // keep track of current day and next day
        int left = 0;
        int right = 1;

        // to calculate the max profit
        int maxProfit = 0;

        // looping from next day till all the days 
        while (right < prices.length) {

            // if price in current day is less than the price in next day
            if (prices[left] < prices[right]) {
                // buy it in the current day and sell it at the next day
                // then the profit would be selling price - buying price
                maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
            } else {
                // else if the current day price is more than the next day, then don't buy anything and go to next day
                left = right;
            }
            // incrementing right pointer (the next days/coming days) at every iteration because to decide which day has the highest price to sell the stock which will maximize the profit
            right++;

        }
        
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {10,1,5,6,7,1};
        System.out.println(maxProfit(prices));
    }
}
