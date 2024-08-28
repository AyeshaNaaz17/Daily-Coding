public class replaceSpaces {

    public static void replaceSpacesURLify (char[] str, int trueLen) {
        // count the spaces from which the new length of the index will be calculated
        int spaceCount = 0;
        int i = 0;
        int index;
        // counting spaces
        for (i = 0; i < trueLen; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        // calculating the new length of the string so that %20 can be added in the place of spaces. trueLen (13) + spaceCount(2) * 2 => 13 + 4 = 17
        // 3 characters to be added -> 1 space already there -> 2 more spaces needed beside the existing 1 space so spaceCount*2
        index = trueLen + spaceCount * 2;

        // removing extra spaces at the end from the given string by adding null character in the last position
        if (trueLen < str.length) {
            str[trueLen] = '\0';
        }

        // iterating from the end to front
        for (i = trueLen - 1; i>0; i--) {
            // if includes spaces
            if (str[i] == ' ') {
                // "Mr%20John%20%Smith"
                // iterating from the end so first 0 then 2 then %
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                // since 3 characters are inserted, decrement the new length by 3
                index = index - 3;
            } else {
                // doesn't include spaces, shift the characters to the right
                str[index - 1] = str[i];
                // decrement the new length each time after shifting a character
                index--;
            }
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        String str = "Mr John Smith     ";
        char charArray[] = str.toCharArray();
        int length = 13;
        replaceSpacesURLify(charArray, length);
    }
}