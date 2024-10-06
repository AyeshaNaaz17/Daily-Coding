import java.util.HashSet;

public class containsDuplicate {

    public static boolean hasDuplicateBrute(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasDuplicateOptimized(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 3};
        System.out.println(hasDuplicateBrute(nums));
        System.out.println(hasDuplicateOptimized(nums));
    }
}
