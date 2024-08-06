import java.util.Arrays;

public class productOfArrayExceptSelf {

    public static int[] productExceptSelf (int[] arr) {
        
        // product of prefix and suffix products
        int[] result = new int[arr.length];

        int[] prefix = new int[arr.length];

        prefix[0] = 1;

        // prefix array product (product of all left elements except self)
        for (int i=1; i<prefix.length; i++) {
            prefix[i] = prefix[i-1] * arr[i-1];
        }

        int suffix = 1;
        // suffix array product (product of all right elements except self)
        for (int i=arr.length-1; i>=0; i--) {
            result[i] = prefix[i] * suffix;
            suffix *= arr[i];
        }

        return result;

    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int[] result = productExceptSelf(nums);
        System.out.println(Arrays.toString(result)); // Output: [120, 60, 40, 30, 24]

        nums = new int[]{3, 2, 1};
        result = productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }
}
