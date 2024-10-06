package StacksQueues;
import java.util.Stack;

public class validParenthesis {
/* You are given a string s consisting of the following characters: '(', ')', '{', '}', '[' and ']'.

The input string s is valid if and only if: 
    - Every open bracket is closed by the same type of close bracket.
    - Open brackets are closed in the correct order.
    - Every close bracket has a corresponding open bracket of the same type.
    - Return true if s is a valid string, and false otherwise.

Example 1:
Input: s = "[]"
Output: true


Example 2:
Input: s = "([{}])"
Output: true

Example 3:
Input: s = "[(])"
Output: false

*/

    public static void main(String[] args) {
        String strs = "([{}])";
        System.out.println(isValid(strs));
        System.out.println(isValidOptimal(strs));
    }

    // this valid parenthesis function checks if there are equal amount of close parenthesis for open parenthesis
    static boolean isValid (String strs) {
        Stack<Character> stack = new Stack<>();
        
        for (char c: strs.toCharArray()) {

            // if it's a opening bracket then push to the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);

            } else {
                // else if it's a closing bracket then check if the stack contains a opening bracket
                // check for all 3 types of bracket one by one
                if (c == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                } 
                
                if (c == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                } 
                
                if (c == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                }
            }
        }
        
        // if the given string of parenthesis is valid then the stack will be empty (returning true)
        return stack.isEmpty();
    }

    // this valid parenthesis function needs one type of bracket closed together (ex: "([{}])";) instead of (ex: "[(])")
    static boolean isValidOptimal (String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            }
            else if (c == '{') {
                stack.push('}');
            }
            else if (c == '[') {
                stack.push(']');
            }
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();

    }

}
