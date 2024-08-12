import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class topKFrequentElements {

    public static int[] topKFrequentElement (int nums[], int k) {
        // HashMap to store the number and the amount of time they occurred in the given array
        Map<Integer, Integer> count = new HashMap<>();
        // An array of type List where in each index it stores the elements occurrences
        // Like for (4, 4, 3) [[], [3], [4], []]; 4 in 2nd index cause it occurred 2 times and 3 in 1st index cause it occurred once
        List<Integer>[] freq = new List[nums.length + 1];
        // Initializing a list at each index of array
        for (int i=0; i<freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0)+1);
        }

        for (Map.Entry<Integer, Integer> entry: count.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }

        int res[] = new int[k];
        int index = 0;

        for (int i=freq.length-1; i>0 && index < k; i--) {
            for (int n: freq[i]) {
                res[index++] = n;
                if (index == k) {
                    return res;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        int[] res = topKFrequentElement(nums, k);
        for (int i=0; i<res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
