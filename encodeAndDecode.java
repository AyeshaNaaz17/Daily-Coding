import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class encodeAndDecode {

    public static String encode(List<String> strs) {

        // disadvantage of having just 1 //# -> let's say the given list of string is ["neet", "co#de"], now if it's encoded it will be "#neet#co#de", if it's decoded based on # (since it's the only special character used for encoding) the result will be ["neet", "co", "de"] which is different from expected result

        // StringBuilder encodeStr = new StringBuilder();
        // for (String s: strs) {
        //     encodeStr.append(s);
        //     encodeStr.append("#");
        // }
        // return encodeStr.toString(); // neet#code#love#you


        // so to overcome that issue, we include the length of that string before including #, that way while decoding, we can get the correct amount of characters present in one word/string

        StringBuilder encodedString = new StringBuilder();
        for (String str : strs) {
            encodedString.append(str.length()).append("#").append(str);
        }
        return encodedString.toString(); // 4#neet4#code4#love3#you
    }

    public static List<String> decode(String str) {
        List<String> decodeStr = new ArrayList<>();
        // pointer to the encoded input string 
        int i = 0;

        // looping the whole string
        while (i < str.length()) {
            // pointer to find the position of the next '#'
            int j = i;
            // the first character in the string is the length of the first string, let's store that in variable i, and we need i number of characters after #, so j will be pointing at next which just present next to the length  
            while (str.charAt(j) != '#') {
                j++;
            }
            // now j is pointing at the next '#', so we need to get the length of the first string
            // substring(i, j) -> excludes j; substring(0, 1) = int(4)
            int length = Integer.valueOf(str.substring(i, j));
            // next the i should point to the next length of the string
            // i = 1 + 1 + 4 = 6 for "4#neet5#co#de"
            i = j + 1 + length;
            // include the characters after # (i.e., next index of j) till before the next length of string 
            decodeStr.add(str.substring(j+1, i));
        }
        return decodeStr;
    }
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>(Arrays.asList("neet", "co#de", "love", "you"));
        System.out.println(encode(strs));
        System.out.println(decode(encode(strs)));
    }
}
