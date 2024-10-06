package StacksQueues;
import java.util.Stack;

public class minimumAddToMakeParenthesisValid {
    public static void main(String[] args) {
        String strs = "(())))))";
        System.out.println(minAddToMakeValid(strs));
    }

    public static int minAddToMakeValid (String s) {

        Stack<Character> stack = new Stack<>();

        for (char c: s.toCharArray()) {
            // if it's a closing ')'
            if (c == ')') {
                // check if the stack contains a '('
                if (!stack.isEmpty() && stack.peek() == '(') {
                    // if it does, then remove as it is balanced
                    stack.pop();
                } else {
                    // adds ')' to stack since '(' is not present and is needed to make valid
                    stack.push(c);
                }
            } else {
                // adds '('
                stack.push(c);
            }
        }

        // all unpaired parenthesis remain in the stack, so the stack size is amount of parenthesis needed to make the parenthesis string valid
        return stack.size();
    }
}
