import java.util.HashMap;

public class longestRepeatingSubstringWithReplacement {

/* You are given a string s consisting of only uppercase english characters and an integer k.   You can choose up to k characters of the string and replace them with any other uppercase       English character.

    After performing at most k replacements, return the length of the longest substring which contains only one distinct character.

    Example 1:
    Input: s = "XYYX", k = 2
    Output: 4
    Explanation: Either replace the 'X's with 'Y's, or replace the 'Y's with 'X's.

    Example 2:
    Input: s = "AAABABB", k = 1
    Output: 5 

*/

public static int characterReplacementArrays(String s, int k) {
    // Array to store the frequency of each character (26 slots for 26 uppercase letters)
    int[] arr = new int[26];
    
    // Variables to keep track of the result and the maximum frequency of any character in the window
    int ans = 0;
    int max = 0;
    
    // i is the left pointer of the window, j is the right pointer
    int i = 0;
    
    // Sliding window starts here, moving j from left to right across the string
    for (int j = 0; j < s.length(); j++) {
        // Increment the count for the character s.charAt(j)
        arr[s.charAt(j) - 'A']++;
        
        // Update the max count (most frequent character in the window)
        max = Math.max(max, arr[s.charAt(j) - 'A']);
        
        // If the number of characters that need to be changed is greater than k, shrink the window
        if (j - i + 1 - max > k) {
            arr[s.charAt(i) - 'A']--; // Reduce the count of the character we're removing from the window
            i++; // Move the left pointer to the right to shrink the window
        }
        
        // Update the answer with the size of the valid window
        ans = Math.max(ans, j - i + 1);
    }
    
    // Return the length of the longest valid window
    return ans;
}


    public static int characterReplacementHashMap(String s, int k) {
        HashMap<Character, Integer> countMap = new HashMap<>();
        int left = 0;
        int maxCount = 0; // max frequency of a single character in the current window: helps us know how many characters we would need to change to make the whole window filled with that character.
        int maxLength = 0;

        // Move the right pointer to expand the window
        for (int right = 0; right < s.length(); right++) {
            // get the character and put it's frequency in the HashMap
            char currentChar = s.charAt(right);
            countMap.put(currentChar, countMap.getOrDefault(currentChar, 0) + 1);
            // to get the most frequent character's frequency 
            maxCount = Math.max(maxCount, countMap.get(currentChar));

            // Check if we need to shrink the window
            int windowSize = right - left + 1;

            // We check if the size of the window (the number of characters between left and right) minus the frequency of the most common character is greater than k. This checks how many characters need to be changed to make all the characters the same

            // if it requires more than k replacements then change the window of characters
            if (windowSize - maxCount > k) {
                // get the character at left pointer
                char leftChar = s.charAt(left);
                // and decrement it's frequency by 1 as we are moving forward
                countMap.put(leftChar, countMap.get(leftChar) - 1);
                // shrink the window by incrementing the left pointer
                left++;
            }

            // Track the maximum length of the valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


    public static void main(String[] args) {
        String s = "AAABABB";
        int k = 1;
        System.out.println(characterReplacementHashMap(s, k)); // Output: 5
        System.out.println(characterReplacementArrays(s, k));
    }
}
