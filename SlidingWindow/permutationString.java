import java.util.Arrays;

public class permutationString {

/* Permutation String

You are given two strings s1 and s2.
Return true if s2 contains a permutation of s1, or false otherwise. That means if a permutation of s1 exists as a substring of s2, then return true. Both strings only contain lowercase letters.

Example 1:
Input: s1 = "abc", s2 = "lecabee"
Output: true
Explanation: The substring "cab" is a permutation of "abc" and is present in "lecabee". */

    private static boolean checkEquals(int[] str1, int[] str2) {
        return Arrays.equals(str1, str2);
    }

    public static boolean checkInclusionBrute(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        // to keep track of frequencies of s1 string's characters
        int[] s1Freq = new int[26];
        // to keep track of frequencies of current window's substring characters
        int[] windowFreq = new int[26];

        // store the frequencies of the characters present in the string
        for (int i=0; i<s1.length(); i++) {
            // for s1
            s1Freq[s1.charAt(i) - 'a']++;
            // do the same for s2, i.e., store the first window's character frequencies
            windowFreq[s2.charAt(i) - 'a']++;
        }

        // check if the first window is itself the permutation
        if (checkEquals(s1Freq, windowFreq)) {
            return true;
        }

        // starting from next element as first 3 of s2 is already in the frequency array
        // to keep the window size same as s1.length(), we will keep removing from the first element
        for (int i=s1.length(); i<s2.length(); i++) {
            windowFreq[s2.charAt(i) - 'a']++;
            // i - s1.length() will remove characters from the start
            windowFreq[s2.charAt(i - s1.length()) - 'a']--;

            // check if they are equal after adding the next character and removing from first
            if (checkEquals(s1Freq, windowFreq)) return true;
        }

        return false;
    }

    public static boolean checkInclusionOptimal(String s1, String s2) {
        // there will no permutation of s1 in s2 if s2 is shorter than s1
        if (s1.length() > s2.length()) {
            return false;
        }

        // two arrays for storing frequencies
        // one for the s1 string
        int[] s1Freq = new int[26];
        // another for current window of s1.length() in s2
        int[] windowFreq = new int[26];

        // calculate the frequency of s1 string
        for (int i = 0; i < s1.length(); i++) {
            s1Freq[s1.charAt(i) - 'a']++;
        }

        // to keep track of matching characters
        int matches = 0;
        // Initially populate the window of size s1.length() in s2
        // get the first characters of s1.length() from the s2
        for (int i = 0; i < s1.length(); i++) {
            int index = s2.charAt(i) - 'a';
            windowFreq[index]++;
        }

        // Count initial matches
        // since there are 26 alphabets, keep track of how many are equal in comparison with s1 and current window of s2 (first insertion of a window done)
        for (int i = 0; i < 26; i++) {
            if (s1Freq[i] == windowFreq[i]) {
                matches++; // counts the equal values i.e., both should be 1 or 0 to increment match (ex: s1Freq[9] = 0 & windowFreq[9] = 0... match += 1), both the s1Freq and the windowFreq should be equal to return true
            }
        }

        // Start sliding the window
        for (int i = s1.length(); i < s2.length(); i++) {
            // both the s1Freq (size 26) and the windowFreq (size 26) should be equal to return true
            if (matches == 26) {
                return true;
            }

            // Right side of window, add new char
            // getting the next char.. sliding the window to right
            int indexRight = s2.charAt(i) - 'a';
            // and storing it's frequency
            windowFreq[indexRight]++;

            // if the value inserted is also present in s1Freq then increment the match
            if (windowFreq[indexRight] == s1Freq[indexRight]) {
                matches++;
            // if the frequency of the existed letter increased in windowFreq and decrement of that value is present in s1Freq then match will be reduced by 1, as for ex: there will be 2 a's in windowFreq and 1 a in s1Freq, they clearly cannot be a permutation  
            } else if (windowFreq[indexRight] - 1 == s1Freq[indexRight]) {
                matches--;
            }

            // Left side of window, remove old char
            // moving the left pointer
            // i - s1.length() to get the index from start
            int indexLeft = s2.charAt(i - s1.length()) - 'a';
            windowFreq[indexLeft]--; // decrement it's frequency as it is no longer considered

            // if the value inserted/changed is also present in s1Freq then increment the match
            if (windowFreq[indexLeft] == s1Freq[indexLeft]) {
                matches++;
            // decrement the match if increased value of the decremented frequency is present in s1Freq, like 1 a in windowFreq and 2 a's in s1Freq then they can't be a match
            } else if (windowFreq[indexLeft] + 1 == s1Freq[indexLeft]) {
                matches--;
            }
        }

        return matches == 26; // both the s1Freq and windowFreq should be equal then matches = 26

    }

    public static void main(String[] args) {
        System.out.println(checkInclusionBrute("abc", "lecabee"));  // Output: true
        System.out.println(checkInclusionOptimal("abc", "lecaabee")); // Output: false
    }
}
