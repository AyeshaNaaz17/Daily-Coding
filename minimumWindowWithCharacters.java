import java.util.HashMap;

public class minimumWindowWithCharacters {

/* Given two strings s and t, return the shortest substring of s such that every character in t, including duplicates, is present in the substring. If such a substring does not exist, return an empty string "". You may assume that the correct output is always unique.

Example 1:
Input: s = "OUZODYXAZV", t = "XYZ"
Output: "YXAZ"
Explanation: "YXAZ" is the shortest substring that includes "X", "Y", and "Z" from string t.

Example 2:
Input: s = "xyz", t = "xyz"
Output: "xyz"
Example 3:

Input: s = "x", t = "xy"
Output: "" */

    public static String minWindow(String s, String t) {

        HashMap<Character, Integer> map = new HashMap<>();

        for (char c: t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int minLen = s.length() + 1; // to store the length of the minimum window (initialized to s.length() + 1)
        int left = 0; // to represent the left boundary of the window.
        int subStr = 0; // to store the starting index of the minimum window.
        int matched = 0; // to count how many characters from t have been matched in the current window.

        // Expand the window by moving the window pointer from left to right.
        for (int window=0; window<s.length(); window++) {

            // get the current character
            char c = s.charAt(window);

            // Update the HashMap and matched count when a character from t is found in current window.
            if (map.containsKey(c)) {

                map.put(s.charAt(window), map.get(c) - 1);
                // if a character is present in t and it's frequency say for example is 1, to verify the character present in current window of s is same as t, we can decrement it's frequency then we know we have found the match of a character. if all characters frequency is 0 in t's frequency hashmap then the match is found in the current window
                if (map.get(c) == 0) matched++;

            }

            // when the match is found 
            while (matched == map.size()) {

                // get the window's size and update minLen
                if (minLen > window - left + 1) {
                    minLen = window - left + 1;
                    // window starting point
                    subStr = left;
                }

                // sliding the window from start, (removing character one by one in the start to get minimum substring containing t )
                // delete the character and increment the starting point of window to next character
                char deleted = s.charAt(left++);

                // remove the character discarded from hashmap
                if (map.containsKey(deleted)) {

                    // if that character is considered for matched variable then decrement it
                    if (map.get(deleted) == 0) 
                        matched--;

                    // and put the frequency of that character back to one i.e., characters original frequency from t so that it will be represented as that character is not present in s yet
                    map.put(deleted, map.get(deleted) + 1);

                }
    
            }

        }

        return minLen > s.length() ? "" : s.substring(subStr, subStr + minLen);
    }

    public static void main(String[] args) {
        String s = "OUZODYXAZV";
        String t = "XYZ";
        System.out.println(minWindow(s, t));
    }
}
