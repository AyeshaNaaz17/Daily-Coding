import java.util.Arrays;
import java.util.HashMap;

public class twoSum {

    public static int[] twoSumBrute(int[] nums, int target) {
        int[] res = new int[2];
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    public static int[] twoSumOptimized(int[] nums, int target) {
        
        // number as key and it's key as value
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i=0; i<nums.length; i++) {

            int num = nums[i];
            int diff = target - num;

            if (hm.containsKey(diff)) {
                return new int[] {
                    // gets the index of diff
                    hm.get(diff), i
                };
            }

            hm.put(num, i);

        }

        return new int[] {};
    }

    // Two Pointers
    public static Boolean twoSumOnePass(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return true;
            }
            else if (sum < target) {
                left++;
            } else {
                right --;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 11, 15};
        int target = 7;

        int[] res =twoSumBrute(nums, target);
        System.out.println("Brute Force Result: " + Arrays.toString(res));

        int[] resOptimized = twoSumOptimized(nums, target);
        System.out.println("Optimized Result: " + Arrays.toString(resOptimized));

        System.out.println("Two pointers: " + twoSumOnePass(nums, target));

    }
}
