import java.util.Arrays;

public class validAnagram {

    public static boolean isAnagramBrute(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char sc[] = s.toCharArray();
        char tc[] = t.toCharArray();

        Arrays.sort(sc);
        Arrays.sort(tc);

        String sa = new String(sc);
        String ta = new String(tc);

        if (sa.equals(ta)) {
            return true;
        }

        return false;

    }

    public static boolean isAnagramOptimized(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int store[] = new int[26];

        for (int i=0; i<s.length(); i++) {
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }

        for (int n: store) {
            if (n != 0) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        String str1 = new String("racecar");
        String str2 = new String("carrace");

        System.out.println(isAnagramBrute(str1, str2));
        System.out.println(isAnagramOptimized(str1, str2));
    }
}
