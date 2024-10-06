package TwoPointers;
public class trappedRainWater {

// You are given an array non-negative integers heights which represent an elevation map. Each value heights[i] represents the height of a bar, which has a width of 1.

// Return the maximum area of water that can be trapped between the bars.

// Example 1:

// Input: height = [0,2,0,3,1,0,1,3,2,1]

// Output: 9

    public static int trappedWaterBrute (int[] heights) {
        
        int  n = heights.length;

        if (heights == null || n == 0) {
            return 0;
        }

        // to keep track of previous bars height 
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // first element does not have a previous element
        leftMax[0] = 0;
        // loop from second element till the end
        for (int i=1; i<n; i++) {
            // store the max height till now
            leftMax[i] = Math.max(leftMax[i-1], heights[i]);
        }

        // last element
        rightMax[n-1] = heights[n-1];
        // loop from second last element till first element
        for (int i=n-2; i>=0; i--) {
            // store the max height till now
            rightMax[i] = Math.max(rightMax[i+1], heights[i]);
        }

        // variable to store the trapped water
        int trapped_water = 0;
        // loop from start to end
        for (int i=0; i<n; i++) {
            // trapped water will be difference between the minimum of left and right bar's height value (boundary) and current position's height
            trapped_water += Math.min(leftMax[i], rightMax[i]) - heights[i];
        }
        
        return trapped_water;
    }

    public static int trap(int[] height) {

        if (height.length == 0 || height == null) {
            return 0;
        }

        // two pointers; one from start and another from end
        int left = 0;
        int right = height.length - 1;

        // to store max height from left bars; updating as we go further than keeping an array
        int leftMax = height[left];
        // to store max height from right bars; updating as we go further than keeping an array
        int rightMax = height[right];

        int trappedWater = 0;

        // loop till left pointer is less than right
        while (left < right) {
            
            // if max left pointer is less than max right pointer, increment left pointer 
            if (leftMax < rightMax) {
                left++;
                // also check the current bar's height, and compare with leftMax and update accordingly
                leftMax = Math.max(leftMax, height[left]);
                // trapped water will be difference of leftMax and current bar's height (Math.min(leftMax, rightMax)) but here we already mentioned in the if condition that leftMax < rightMax so leftMax is already minimum 
                trappedWater += leftMax - height[left];
            } else {
                // if max right pointer is less than max left pointer or if both left max pointer and right max pointer are same then, decrement right pointer 
                right--;
                // also check the current bar's height, and compare with rightMax and update accordingly
                rightMax = Math.max(rightMax, height[right]);
                // trapped water will be difference of rightMax and current bar's height (Math.min(leftMax, rightMax)) but here we already mentioned in the if condition that rightMax < leftMax or leftMax == rightMax so rightMax is already minimum                 
                trappedWater += rightMax - height[right];
            }

        }

        return trappedWater;

    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trappedWaterBrute(height));  // Output: 6
        System.out.println(trap(height));
    }
}
