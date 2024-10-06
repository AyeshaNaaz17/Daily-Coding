import java.util.Stack;

public class largestRectangleInHistogram {

/* You are given an array of integers heights where heights[i] represents the height of a bar. The width of each bar is 1. Return the area of the largest rectangle that can be formed among the bars.

Example 1:
Input: heights = [7,1,7,2,2,4]
Output: 8

Example 2:
Input: heights = [1,3,7]
Output: 7 
*/

    static int largestareaBrute(int arr[]) {

        int maxArea = 0;
        int n = arr.length;
        // iterating through all the bars
        for (int i = 0; i < n; i++) {
            // considering the bar with minimum height
            int minHeight = Integer.MAX_VALUE;
            // make a rectangle starting from bar 1 (i) to n
            // make a rectangle starting from bar 2 (i) to n etc
            for (int j = i; j < n; j++) {
                // take the min height of the bars
                minHeight = Math.min(minHeight, arr[j]);
                // j- i + 1 -> width
                // area = height * width = the minHeight * all the bars width which contains minHeight in their bars
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    // in the brute force we calculate rectangle's area, in optimal solution we will only calculate the greatest area a bar could give
    public static int largestRectangleAreaOptimalOne (int[] heights) {
        // to store the indices of the bars
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        // push the first index to the stack
        stack.push(0);

        // iterate through the next index
        for (int i=1; i<heights.length; i++) {
            // if the current bar is less than bar present on the stack
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                // calculate how big a rectangle you could make with the bars in the stack
                maxArea = getMax(heights, stack, maxArea, i);
            }
            // add the current bar's index to the stack
            stack.push(i);
        }
        
        // edge case - if any of the bars missed
        // calculate for the left out bars too
        int i = heights.length;
        while (!stack.isEmpty()) {
            maxArea = getMax(heights, stack, maxArea, i);
        }

        return maxArea;
    }

    private static int getMax(int[] heights, Stack<Integer> stack, int maxArea, int i) {
        
        int area;
        // Pop the top of the stack (this is the height for the current rectangle)
        int popped = stack.pop();

         // If the stack is empty, the width is `i` (entire length so far)
        if (stack.isEmpty()) {
            area = heights[popped] * i;
        } else {
            // Otherwise, the width is the difference between the current index and the index of the new top of the stack
            area = heights[popped] * (i - stack.peek() - 1);
        }

        // Return the maximum of the current area and the previous maximum
        return Math.max(area, maxArea);
    }

/* Understanding the Problem 

    * We are given an array of heights where each element represents the height of a bar in a histogram. All bars are of width 1.
    * We need to find the largest rectangular area that can be formed by choosing one or more consecutive bars in the histogram.

Intuition for Using a Stack

    * The core idea is to find how far a bar can extend to the left and right before encountering a shorter bar, as this will define the width of the rectangle for that bar as the shortest height within that range.

Key Insight:

For each bar, we need to find the next shorter bar on both the left and right sides. The rectangle for a bar will extend between these shorter bars.
Using a Stack to Track Bars:

As we traverse the bars from left to right, we use a stack to keep track of the bars in increasing order of height.

Whenever we encounter a bar that is shorter than the bar at the top of the stack, it means we have found the right boundary for the rectangle formed by the bar at the top of the stack.
By popping this bar from the stack, we know:

The height of the rectangle is the height of this popped bar.
The width is determined by the distance between the current position and the next bar in the stack, which represents the left boundary for this rectangle.

Calculating Areas:

For each bar popped from the stack, calculate the area of the rectangle it can form:
If there are no bars left in the stack, it means this bar can extend to the beginning of the histogram.
If there are bars left in the stack, the rectangle extends to the position right after the next bar in the stack.
Continue this process until you’ve handled all bars in the histogram.
Completing the Calculation:

After traversing all bars, any bars left in the stack can form rectangles that extend to the end of the histogram.
Calculate areas for these remaining bars in the same way.
Visualizing with an Example
Consider heights = [2, 1, 5, 6, 2, 3]:

Start pushing heights onto the stack.
When you reach a bar shorter than the top of the stack, pop the top bar and calculate the area considering it as the smallest bar.
Continue until the entire histogram is processed.
Using this stack-based approach ensures that each bar is processed only once, which makes it efficient.

Why This Approach Works
This approach leverages the stack to efficiently track potential rectangular boundaries by storing indices of increasing heights and then calculating the largest possible rectangle whenever a shorter bar (or the end of the histogram) is reached. This ensures that we don't need to re-evaluate heights repeatedly, making the solution optimal with a linear time complexity of 

O(n). */

/* 
 * Dry run: 
 * finding the largest rectangle area for heights = [2, 1, 5, 6, 2, 3] step-by-step:

Initialization
We initialize maxArea = 0 to store the maximum area found.
We use a stack to keep track of each bar’s starting index and height as we traverse the array.
Step-by-Step Dry Run
Processing bar at index 0 (height = 2):

Stack is empty, so push (0, 2).
Stack: [(0, 2)]
Processing bar at index 1 (height = 1):

The current bar (1) is shorter than the top of the stack (2), so we need to calculate the area for bar 0.
Pop (0, 2) from the stack. The width is 1 (from index 0 to index 1), and the height is 2.
Area = 2 * 1 = 2
Update maxArea = max(0, 2) = 2
Push (0, 1) because this bar’s starting index extends back to 0.
Stack: [(0, 1)]
Processing bar at index 2 (height = 5):

The current bar (5) is taller than the top of the stack (1), so push (2, 5).
Stack: [(0, 1), (2, 5)]
Processing bar at index 3 (height = 6):

The current bar (6) is taller than the top of the stack (5), so push (3, 6).
Stack: [(0, 1), (2, 5), (3, 6)]
Processing bar at index 4 (height = 2):

The current bar (2) is shorter than the top of the stack (6), so we start popping:
Pop (3, 6). The width is 1 (from index 3 to index 4), and the height is 6.
Area = 6 * 1 = 6
Update maxArea = max(2, 6) = 6
The current bar (2) is still shorter than the new top (5), so pop (2, 5):
The width is 2 (from index 2 to index 4), and the height is 5.
Area = 5 * 2 = 10
Update maxArea = max(6, 10) = 10
Push (2, 2) because this bar’s starting index extends back to 2.
Stack: [(0, 1), (2, 2)]
Processing bar at index 5 (height = 3):

The current bar (3) is taller than the top of the stack (2), so push (5, 3).
Stack: [(0, 1), (2, 2), (5, 3)]
End of Array: Final Clean-up

Now that we’ve processed all bars, we pop remaining items in the stack:
Pop (5, 3): The width is 1 (from index 5 to the end), and the height is 3.
Area = 3 * 1 = 3
maxArea remains 10
Stack: [(0, 1), (2, 2)]
Next, pop (2, 2): The width is 4 (from index 2 to the end), and the height is 2.
Area = 2 * 4 = 8
maxArea remains 10
Stack: [(0, 1)]
Finally, pop (0, 1): The width is 6 (from index 0 to the end), and the height is 1.
Area = 1 * 6 = 6
maxArea remains 10
Final Result
After processing all bars, the maximum area is 10. This is the largest possible rectangular area in the histogram.
 */

    public static int largestRectangleAreaOptimalTwo(int[] heights) {
         // Initialize maximum area variable
        int maxArea = 0;
         // Stack to store pairs of (index, height)
        Stack<int[]> stack = new Stack<>();

         // Iterate over all bars in the heights array
        for (int i = 0; i < heights.length; i++) {
            int start = i;
            // While stack is not empty and current height is less than the height at the top of the stack
            while (!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                // Pop the stack and calculate the area
                int[] top = stack.pop();
                int index = top[0];  // Starting index of the popped height
                int height = top[1]; // Height of the popped element
                // Calculate area with the popped height and width from the start index to current index
                maxArea = Math.max(maxArea, height * (i - index));
                // Set 'start' to the original index of the popped element for future pushes
                start = index;
            }
            // Push the current bar onto the stack with its start index
            stack.push(new int[]{start, heights[i]});
        }

        // Final pass over the stack for any remaining elements
        while (!stack.isEmpty()) {
            int[] pair = stack.pop();
            int index = pair[0]; // Start index of this height
            int height = pair[1]; // Height at this position
            // Calculate the area considering width from the index to the end of the array
            maxArea = Math.max(maxArea, height * (heights.length - index));
        }

        // Return the maximum area found
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Brute Force: " + largestareaBrute(heights));
        System.out.println("Optimal One: " + largestRectangleAreaOptimalOne(heights));
        System.out.println("Optimal Two: " + largestRectangleAreaOptimalTwo(heights));
    }
}
