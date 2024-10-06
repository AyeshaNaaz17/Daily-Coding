package TwoPointers;
public class maxContainerWater {

// You are given an integer array heights where heights[i] represents the height of the i'th bar.

// You may choose any two bars to form a container. Return the maximum amount of water a container can store.

// Example 1:

// Input: height = [1,7,2,5,4,7,3,6]

// Output: 36

    @SuppressWarnings("unused")
    public static int maxArea(int[] heights) {
        // variable to store maximum water
        int water = 0;
        // two pointers from start and end
        int left = 0;
        int right = heights.length - 1;

        // while left pointer is less than right pointer
        while (left < right) {
            // calculate the area of the rectangle to know how much water will be stored in the container
            // right - left is the width of one bar
            // then for height take the minimum value of the left pointers value (bar) and right pointers value (bar) cause amount of water will be stored between left pointer value and right pointer value 
            int area = (right - left) * Math.min(heights[left], heights[right]);
            // take max of each area 
            water = Math.max(water, area);
            // if left pointers height is less than right then move the left pointer to right
            // greater the height, more the water storage
            if (heights[left] < heights[right]) left++;
            // else if right pointers height is less than left or if left and right pointer values are equal then move right pointer to left
            else right--;
        }

        return water;
    }

    public static void main(String[] args) {
        int[] heights = {1,7,2,5,4,7,3,6};
        System.out.println(maxArea(heights));
    }
}
