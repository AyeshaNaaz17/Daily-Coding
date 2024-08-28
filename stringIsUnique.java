import java.util.HashSet;

public class stringIsUnique {

    public static boolean isUniqueViaHashSet(String str) {
        HashSet<Character> set = new HashSet<>();

        char arr[] = str.toCharArray();

        for (int i=0; i<arr.length; i++) {
            if (set.contains(arr[i])) {
                return false;
            }
            set.add(arr[i]);
        }

        return true;
    }

    public static boolean isUniqueViaArrays(String str) {
        
        // 128 characters
        if (str.length() > 128) {
            return false;
        }

        boolean[] char_set = new boolean[128];

        for (int i=0; i<str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        
        return true;
    }

    
    // only applicable for lowercase letters
    public static boolean isUniqueViaBitVector(String str) {

        // keeps track of seen characters
        int checker = 0;

        for (int i=0; i<str.length(); i++) {

            // gives value between 0 and 25
            int val = str.charAt(i) - 'a';

            // checking if checker's value and current characters value is same or not
            if ((checker & (1 << val)) > 0) {
                // if bit greater than zero then string contains duplicate
                return false;
            }

            checker |= (1 << val);

        }
        
        return true;
    }

    public static void main(String[] args) {
        String str = "unique";
        System.out.println(isUniqueViaHashSet(str));
        System.out.println(isUniqueViaArrays(str));
        System.out.println(isUniqueViaBitVector(str));
    }
}