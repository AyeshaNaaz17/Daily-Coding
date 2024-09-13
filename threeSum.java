import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class threeSum {

    public static List<List<Integer>> threeSumBruteForce(int[] nums, int n) {
        // HashSet to store the triplets so that they aren't duplicated
        HashSet<List<Integer>> set = new HashSet<>();

        // three loops; one: from start to end, two: from next element of first for loop to end, third: from next element of third for loop to end
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                for (int k=j+1; k<n; k++) {
                    // check if the sum of three numbers from the array is zero
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // if it is then make a temporary list and add those numbers
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k]);
                        // sort them
                        temp.sort(null);
                        // add the triplet obtained to the HashSet
                        set.add(temp);
                    }
                }
            }
        }
        // return the set values as a List
        return new ArrayList<>(set);
    }

    public static List<List<Integer>> threeSumTwoPointers (int[] nums) {
        // sort them to avoid duplicates
        Arrays.sort(nums);
        // to store the result
        List<List<Integer>> res = new ArrayList<>();

        // iterate through start to end
        for (int i=0; i<nums.length; i++) {
            // if it's not the first element and current element is same as previous element then skip
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            // two pointers for 2nd element and 3rd element from start and end with i of for loop fixed
            int l = i + 1;
            int r = nums.length - 1;

            // while left pointer is less than right pointer 
            while (l < r) {
                // calculate the current sum of elements at i from for loop and two pointers
                int sum = nums[i] + nums[l] + nums[r];

                // if sum is > 0 then decrease the right pointer (as the array is sorted, right pointer will be pointing to large numbers so decrease them)
                if (sum > 0) {
                    r--;
                } else if (sum < 0) { // if sum is < 0 then increase the left pointer (increasing elements value)
                    l++;
                } else { // if it's 0 then convert those 3 array elements to list and add it to resultant array
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // once done with adding one triplet, go for obtaining next elements by changing the incrementing and decrementing the pointer values
                    l++;
                    r--;

                    // avoid duplicate elements at left and right pointers
                    // notice in left, we will be checking the left previous value
                    while (l < r && nums[l] == nums[l-1]) l++;
                    // and on right, we will check the right previous value
                    while (l < r && nums[r] == nums[r+1]) r--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { -1, 0, 1, 2, -1, -4};
        int n = arr.length;

        List<List<Integer>> ans = threeSumBruteForce(arr, n);
        System.out.println(ans);
    
        ans = threeSumTwoPointers(arr);
        System.out.println(ans);

    }
}
