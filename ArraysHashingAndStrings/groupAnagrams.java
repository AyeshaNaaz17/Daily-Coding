import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class groupAnagrams {

    public static List<List<String>> groupingAnagrams(String[] strs) {
        // stores value array of a string from str as key and str as value added to list
        HashMap<String, List<String>> anagrams = new HashMap<>();

        // Iterate through each element from the string array
        for (String s: strs) {
            // Initialize a count array of size 26 for each element to know the frequency of the present letters
            int[] count = new int[26];
            // convert that string to char array
            for (char c: s.toCharArray()) {
                // and increment the value in the count array 
                count[c - 'a']++;
            }
            // convert that count array (one element's count array) to string and store it as key: "[1, 0, 0, 1,...]"
            String key = Arrays.toString(count);
            // if key is not present in the map, add it with while creating a new list for it as value
            if (!anagrams.containsKey(key)) {
                anagrams.put(key, new ArrayList<>());
            }
            // add the string to the list of the key
            // works for both cases, key present and key not present
            anagrams.get(key).add(s);
        }
        // return the values in the form of a arraylist
        return new ArrayList<>(anagrams.values());
    }

    public static void main(String[] args) {
        String strs[] = {"act","pots","tops","cat","stop","hat"};
        System.out.println(groupingAnagrams(strs));
    }
}
