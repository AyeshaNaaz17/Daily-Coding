import java.util.Arrays;

public class productOfArrayExceptSelf {

    
    // works when array contains no zeroes
    public static int[] productExceptSelfWithDivision (int[] arr) {
        
        // to store resultant product
        int[] result = new int[arr.length];

        // get the total product i.e., product of all elements present in the array in a variable
        int totalProduct = 1;
        for (int i=0; i<arr.length; i++) {
            totalProduct *= arr[i];
        }

        // divide the totalProduct by current element to exclude it from having it's value multiplied with other elements (making it product array except self)
        for (int i=0; i<arr.length; i++) {
            result[i] = totalProduct / arr[i];
        }

        return result;

    }

    public static int[] productExceptSelfWithoutDivisionBrute (int[] arr) {
        
        int n = arr.length;
        // resultant array to store products
        int[] result = new int[n];

        // to store all the product of elements that are left of current element (excludes current element)
        int[] left_products = new int[n];
        // to store all the product of elements that are right of current element (excludes current element)
        int[] right_products = new int[n];


        // no element present left of first element in arr and no right element present beside last element of arr 
        left_products[0] = 1;
        right_products[n-1] = 1;

        // calculate left product array
        for (int i=1; i<n; i++) {
            left_products[i] = arr[i-1] * left_products[i-1];
        }

        // calculate left product array
        for (int i=n-2; i>=0; i--) {
            right_products[i] = right_products[i+1] * arr[i+1];
        }

        // calculate resultant product array by multiplying left product array and right product array
        // we get the desired result because both left_product array and right_product array excluded the current element in the process, making them product array except self
        for (int i=0; i<n; i++) {
            result[i] = left_products[i] * right_products[i];
        }

        return result;

    }
    
    // reduced time complexity - 1
    public static int[] productExceptSelfWithoutDivisionOptimalOne (int[] arr) {
        
        // product of prefix and suffix products
        int[] result = new int[arr.length];

        int[] left = new int[arr.length];

        left[0] = 1;

        // prefix array product (product of all left elements except self)
        for (int i=1; i<left.length; i++) {
            left[i] = left[i-1] * arr[i-1];
        }

        int right = 1;
        // suffix array product (product of all right elements except self)
        for (int i=arr.length-1; i>=0; i--) {
            result[i] = left[i] * right;
            right *= arr[i];
        }

        return result;

    }

    // reduced time complexity - 1
    public static int[] productExceptSelfWithoutDivisionOptimalTwo (int[] arr) {
        
        // product of prefix and suffix products
        int[] result = new int[arr.length];

        result[0] = 1;

        // left array product (product of all left elements except self)
        for (int i=1; i<arr.length; i++) {
            result[i] = result[i-1] * arr[i-1];
        }

        int R = 1;
        // right array product (product of all right elements except self) via variable
        for (int i=arr.length-1; i>=0; i--) {
            result[i] = result[i] * R;
            // multiply the right products along the way
            R *= arr[i];
        }

        return result;

    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int[] result = productExceptSelfWithoutDivisionOptimalOne(nums);
        System.out.println(Arrays.toString(result)); // Output: [120, 60, 40, 30, 24]

        nums = new int[]{1, 2, 3};
        result = productExceptSelfWithDivision(nums);
        System.out.println(Arrays.toString(result));

        result = productExceptSelfWithoutDivisionBrute(nums);
        System.out.println(Arrays.toString(result));

        result = productExceptSelfWithoutDivisionOptimalTwo(nums);
        System.out.println(Arrays.toString(result));
        

    }
}