import java.util.Arrays;

public class twoIntegerSumII {

    // Given an array of integers numbers that is sorted in non-decreasing order.

    // Return the indices (1-indexed) of two numbers, [index1, index2], such that they add up to a given target number target and index1 < index2. Note that index1 and index2 cannot be equal, therefore you may not use the same element twice.

    // There will always be exactly one valid solution.

    // Your solution must use O(1) additional space.

    public static int[] twoSum(int[] numbers, int target) {
        // two pointers, at the start and at the end
        int left = 0;
        int right = numbers.length - 1;

        // loop till start is less than end (stopping when start pointer value = end pointer value)
        while (left < right) {
            // calculate the sum of the two pointers
            int sum = numbers[left] + numbers[right];
            // check with the target, since this 1 indexing array, increment the index by one while returning
            if (sum == target) {
                return new int[]{left+1, right+1};
            }
            // if sum > target then move right pointer towards left as this array is sorted
            if (sum > target) {
                right--;
            } else if (sum < target) { // if sum < target, then move start (left) pointer towards right as it contains larger numbers
                left++;
            }
        }
        return new int[]{}; // return an empty array if sum not found
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4};
        int target = 3;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }
}
