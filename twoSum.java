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
        
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i=0; i<nums.length; i++) {

            int num = nums[i];
            int diff = target - num;

            if (hm.containsKey(diff)) {
                return new int[] {
                    hm.get(diff), i
                };
            }

            hm.put(num, i);

        }

        return new int[] {};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 18;

        int[] res =twoSumBrute(nums, target);
        System.out.println("Brute Force Result: " + Arrays.toString(res));

        int[] resOptimized = twoSumOptimized(nums, target);
        System.out.println("Optimized Result: " + Arrays.toString(resOptimized));

    }
}
