import java.util.Arrays;

public class stringPermutation {

    public static Boolean checkPermutationBrute (String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        String a = Arrays.toString(charArray1);
        String b = Arrays.toString(charArray2);

        if (a.equals(b)) {
            return true;
        }
        return false;
    }

    public static Boolean checkPermutationOptimal (String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        // assuming ascii character set
        int[] letters = new int[128];

        // convert one string to char array
        char[] str1_array = str1.toCharArray();

        // iterate each character in the array
        for (char c: str1_array) {
            // System.out.println("c:" + c);
            // System.out.println("int c(" + c + "): " + (int)c);

            // and increment the value in their ascii place
            // letters[97] i.e., a's ascii value
            letters[c]++;
        }

        // Iterate through the second string
        for (int i=0; i<str2.length(); i++) {
            // convert to it's ascii value
            int c = (int)str2.charAt(i);
            // decrement by 1, letters array would contain 0 (goes from 1 to 0) if the strings are permuted else they would go from 0 to -1
            letters[c]--;

            // if negative then strings are not permuted
            if (letters[c] < 0) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "acb";
        System.out.println(checkPermutationBrute(str1, str2));
        System.out.println(checkPermutationOptimal(str1, str2));
    }
}
