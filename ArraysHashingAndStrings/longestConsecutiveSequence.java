import java.util.HashSet;

public class longestConsecutiveSequence {

    public static boolean linearSearch (int arr[], int ele) {
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == ele) {
                return true;
            }
        }
        return false;
    }

    public static int longestConsecutiveBrute (int[] nums) {
        // to keep track longest consecutive length
        int longest = 1;
        // iterate over the array
        for (int i=0; i<nums.length; i++) {
            // store the current element in a varible
            int ele = nums[i];
            // subsequence count of current element
            int count = 1;
            // search if current element + 1 (next element of current element) is present in the array or not with the help of linear search algorithm
            while (linearSearch(nums, ele+1)) { // if returned true
                // if yes, then increment the current element to the we searched for so then search will go on for it's next element
                ele += 1;
                // increment the subsequent count for that element
                count += 1;
            }
            // max of current element's subsequent count to longest variable/previous element's subsequent's count
            longest = Math.max(ele, count);
        }
        return longest;
    }

    public static int longestConsecutiveOptimal (int[] nums) {

        // HashSet to store distinct elements from the array
        HashSet<Integer> set = new HashSet<>();

        // entering distinct elements from array to HashSet
        for (int num: nums) {
            if (!set.contains(num)) {
                set.add(num);
            }
        }

        // to keep track of the count of longest sequence 
        int longest = 0;

        // iterating over HashSet values
        for (int n: set) { // n =2
            // if the number is the start of the sequence then it shouldn't contain the number previous to it
            if (!set.contains(n-1)) { // (2-1=1) set.contains(1): false
                // length variable to increment to the next digit of the number to obtain sequence
                int length = 1;
                // checking if set contains the next digit
                while (set.contains(n+length)) {
                // if it contains then increment
                // set.contins(2+1=3) true; set.contains(2+2=4) true; set.contains(2+3=5) true; set.contains(2+4=6) false
                    length++;
                // length = 2/3/4
                }
                longest = Math.max(length, longest); // max(0, 4)
                // then next iteration for n = 20, (set.contains(21) - false) ; for n = 4 (4-1=3 -> set.contains(3)) skip; etc
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {2,20,4,10,3,4,5};
        System.out.println(longestConsecutiveOptimal(nums));
    }
}
