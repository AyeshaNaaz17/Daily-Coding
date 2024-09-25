import java.util.HashSet;

public class longestSubstringWithoutDuplicates {

    public static int lengthOfLongestSubstring(String s) {

        // HashSet to store unique characters and HashSet instead of Lists because HashSet operations take O(1) time while Lists operation take O(n)
        HashSet<Character> set = new HashSet<>();

        // to keep track of length of longest substring
        int longest = 0;

        // two pointers for sliding window
        int l = 0;
        int r = 0;

        // iterating from the start to end of the string
        while (r < s.length()) {
            // check if the HashSet already contains that character, if not then add it to the set and increment second pointer to find rest of the substring
            if (!set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                r++;
                // keep track of length of the substring, set will contain unique characters
                longest = Math.max(longest, set.size());
            } else {
                // if set already contains the character then start removing the character from the start (i.e., the first pointer) till the duplicate is found
                set.remove(s.charAt(l));
                // and increment the first pointer after each removal till the duplicate is found
                l++;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        String s = "zxyzxyz";
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring(str));
    }
}
