import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class topKFrequentElements {

    public static int[] topKFrequentElementBrute (int nums[], int k) {
        
        // HashMap to store the frequency of the elements
        HashMap<Integer, Integer> frequency = new HashMap<>();

        // entering values in hashmap, element as key and their frequency as their value
        for (int n: nums) {
            frequency.put(n, frequency.getOrDefault(n, 0) + 1);
        }

        // sorting the values in hashmap so the top most frequent element will be easier to get
        List<Map.Entry<Integer, Integer>> frequencyList = new ArrayList<>(frequency.entrySet());
        frequencyList.sort((a, b) -> b.getValue() - a.getValue()); // Sort by frequency in descending order

        // resultant array to store the elements
        int res[] = new int[k];
        for (int i=0; i<res.length; i++) {
            res[i] = frequencyList.get(i).getKey();
        }

        return res;

    }

    public static int[] topKFrequentElementOptimal (int nums[], int k) {
        
        // HashMap to store the frequency of the elements
        HashMap<Integer, Integer> frequency = new HashMap<>();

        // An array of list type (using bucket sort algorithm) 
        List<Integer>[] bucket = new List[nums.length+1];

        // entering values in hashmap, element as key and their frequency as their value
        for (int n: nums) {
            frequency.put(n, frequency.getOrDefault(n, 0) + 1);
        }

        // iterating over hashmap keys
        for (int key: frequency.keySet()) {
            // getting the value of the key
            int frequencyOfKey = frequency.get(key);
            // create a list at the position of array 
            if (bucket[frequencyOfKey] == null) {
                bucket[frequencyOfKey] = new ArrayList<>();
            }
            // add the key at the index same as it's frequency
            bucket[frequencyOfKey].add(key);
        }

        // resultant array to store the elements
        int res[] = new int[k];
        int count = 0;

        // iterating from end because we need the elements which occurred most, so they will be present in higher index; higher the frequency, nearer to the end of the array
        for (int i=bucket.length-1; i>=0 && count < k; i--) {
            // check if the list at that index of array is empty or not
            // if not
            if (bucket[i] != null) {
                // there are list of numbers at that index
                for (Integer ele: bucket[i]) {
                    // store them in the result array
                    res[count++] = ele;
                    if (count == k) {
                        return res;
                    }
                }
            }
        }

        return res;

    }
    public static void main(String[] args) {
        // int[] nums = {1,1,1,2,2,3,3,4};
        int[] nums = {1,1,2,2};
        int k = 1;
        int[] res = topKFrequentElementOptimal(nums, k);
        for (int i=0; i<res.length; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
        nums = new int[]{1,2,1,3,3};
        k = 2;
        int result[] = topKFrequentElementBrute(nums, k);
        System.out.println(Arrays.toString(result));
    }
}