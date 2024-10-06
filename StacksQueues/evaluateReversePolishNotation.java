package StacksQueues;
import java.util.Stack;

/* You are given an array of strings tokens that represents a valid arithmetic expression in Reverse Polish Notation.

Return the integer that represents the evaluation of the expression.

The operands may be integers or the results of other operations.
The operators include '+', '-', '*', and '/'.
Assume that division between integers always truncates toward zero.

Example 1:
Input: tokens = ["1","2","+","3","*","4","-"]
Output: 5
Explanation: ((1 + 2) * 3) - 4 = 5 */

public class evaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = {"1","2","+","3","*","4","-"}; // ((2+1)*3) = 9
        System.out.println(evalRPNBrute(tokens));
        System.out.println(evalRPNOptimal(tokens));
    }

    public static int evalRPNBrute(String[] tokens) {

        // stack needed because we need previous elements in a sequence
        Stack<Integer> postfix = new Stack<>();

        // loop through the array
        for (String s : tokens) {

            // while looping if it's the operator then
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {

                // pop out two elements since all of them are binary operators
                int num1 = postfix.pop();
                int num2 = postfix.pop();

                // to store final answer
                int ans = 0;

                // switch case to decide which operation to perform
                switch (s) {
                    case "+":
                        ans += num1 + num2;
                        break;

                    // since num2 is inserted before num1
                    case "-":
                        ans += num2 - num1;
                        break;

                    case "*":
                        ans += num1 * num2;
                        break;

                    // since num2 is inserted before num1
                    case "/":
                        ans += (int)(num2 / num1);
                        break;
                }

                // keep pushing the ans after each operation so next operation will be performed on that answer
                postfix.push(ans);
            } else {
                // else convert the numbers from string to integer and push into the stack 
                postfix.push(Integer.parseInt(s));
            }
        }

        // after all the operations are performed, only the answer will be present in the stack so pop that and return
        return postfix.pop();
    }

    // without switch case
    public static int evalRPNOptimal(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String s: tokens) {
            if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } 
            else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } 
            else if (s.equals("-")) {
                int n1 = stack.pop();
                int n2 = stack.pop();
                stack.push(n2 - n1);
            }
            else if (s.equals("/")) {
                int n1 = stack.pop();
                int n2 = stack.pop();
                stack.push((int)(n2 / n1));
            } 
            else {
                stack.push(Integer.parseInt(s));
            }
        }

        return stack.pop();
    }

}
