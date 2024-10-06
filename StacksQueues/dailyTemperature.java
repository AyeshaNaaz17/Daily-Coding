package StacksQueues;
// import java.util.Stack;

/* You are given an array of integers temperatures where temperatures[i] represents the daily temperatures on the ith day.

Return an array result where result[i] is the number of days after the ith day before a warmer temperature appears on a future day. If there is no day in the future where a warmer temperature will appear for the ith day, set result[i] to 0 instead.

Example 1:
Input: temperatures = [30,38,30,36,35,40,28]
Output: [1,4,1,2,1,0,0]

Example 2:
Input: temperatures = [22,21,20]
Output: [0,0,0] */

import java.util.Arrays;
import java.util.Stack;

public class dailyTemperature {

    public static void main(String[] args) {
        int[] temperatures = {30,38,30,36,35,40,28};
        int[] result = dailyTemperaturesBrute(temperatures);
        System.out.println(Arrays.toString(result));
        result = dailyTemperaturesOptimal(temperatures);
        System.out.println(Arrays.toString(result));
    }

    public static int[] dailyTemperaturesBrute(int[] temperatures) {

        int[] result = new int[temperatures.length];

        // loop through the array
        for (int i=0; i<temperatures.length; i++) {
            // loop through the next element to find greater element than current
            for (int j=i+1; j<temperatures.length; j++) {
                // if it is then calculate how difference between the indices  
                if (temperatures[j] > temperatures[i]) {
                    // one element i.e., the first element greater than current element is enough so break the loop 
                    result[i] = j - i;
                    break;
                } else {
                    // else since no greater element is found then put 0
                    result[i] = 0;
                }
            }
        }

        return result;
    }

    public static int[] dailyTemperaturesOptimal(int[] temperatures) {
        // Create an array to store the result for each day (number of days to wait for a warmer temperature)
        int[] res = new int[temperatures.length];
        
        // Create a stack to store pairs of [temperature, index]
        Stack<int[]> stack = new Stack<>();

        // Loop through each day in the temperatures array
        for (int i = 0; i < temperatures.length; i++) {

            int currentTemp = temperatures[i];  // Current day's temperature
            
            // While the stack is not empty and today's temperature is greater (warmer) than the top of the stack
            while (!stack.isEmpty() && currentTemp > stack.peek()[0]) {
                // Pop the lesser (colder) temperature from the stack
                int[] pair = stack.pop();
                
                // Calculate how many days it took to find this warmer day
                // subtract the indices
                res[pair[1]] = i - pair[1];
            }
            
            // Push the current day's temperature and its index to the stack
            stack.push(new int[]{currentTemp, i});
        }
        
        // Return the result array
        return res;
    }

}
