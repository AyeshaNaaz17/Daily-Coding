public class isPalindrome {

    // Given a string s, return true if it is a palindrome, otherwise return false.

    // A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.

    public static boolean isPalindromeBrute(String s) {
        // converting the input string to lowercase
        String palindrome = s.toLowerCase();
        // removing characters like ?, ! etc
        String ans = palindrome.replaceAll("[^a-z]", "");
        // get the modified string's length
        int len = ans.length();
        // check the first character with the last character and increment them towards each other
        for (int i=0; i<len/2; i++) {
            if (ans.charAt(i) != ans.charAt(len-i-1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeTwoPointers(String s) {
        
        // modifying the string according to the question
        String str = s.toLowerCase();
        str = str.replaceAll("[^a-z]", "");

        // two pointers, one at the start and other at the end of the string
        int left = 0;
        int right = str.length()-1;

        // looping till starting pointer is less than the ending pointer
        while (left < right) {
            // checking the first pointer's character with the last as palindrome means getting the same word if read backwards
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            } else {
                // if it's same then start checking the next character i.e., move the start pointer towards right one by one and move the right pointer towards left one by one
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "Was it a car or a cat I saw?";
        System.out.println(isPalindromeBrute(s));
        System.out.println(isPalindromeTwoPointers(s));
    }
}
