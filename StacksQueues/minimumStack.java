package StacksQueues;
import java.util.Stack;

/* Design a stack class that supports the push, pop, top, and getMin operations.

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
Each function should run in O(1) time. */

public class minimumStack {

    // to push all the numbers
    private Stack<Integer> stack;
    // to keep track of minimum numbers
    private Stack<Integer> minStack;

    // constructor
    public minimumStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    // pushing the val into the stack
    public void push (int val) {
        stack.push(val);
        // If minStack is empty, the value itself is the minimum.
        // Get the current minimum from the top of minStack.
        // If the new value is smaller, push it as the new minimum.
        // Otherwise, push the existing minimum again.
        /* similar to: 
            if (minStack.isEmpty()) {
                minStack.push(val); // If minStack is empty, the value itself is the minimum.
            } else {
                int currentMin = minStack.peek();  // Get the current minimum from the top of minStack.
                if (val < currentMin) {
                    minStack.push(val);  // If the new value is smaller, push it as the new minimum.
                } else {
                    minStack.push(currentMin);  // Otherwise, push the existing minimum again.
                }
            }

         */
        val = Math.min(val, minStack.isEmpty() ? val : minStack.peek());
        minStack.push(val);
    }

    // removing the number from both the stacks
    public void pop () {
        stack.pop();
        minStack.pop();
    }

    // top most element
    public int top () {
        return stack.peek();
    }

    // min element at the top of minStack
    public int getMin () {
        return minStack.peek();
    }


    public static void main(String[] args) {
        minimumStack minStack = new minimumStack();

        minStack.push(1);
        minStack.push(2);
        minStack.push(0);
        
        // Get the minimum value
        System.out.println("Minimum value: " + minStack.getMin()); // Should print 0
        
        // Pop the top value
        minStack.pop();
        
        // Get the top value
        System.out.println("Top value: " + minStack.top()); // Should print 2
        
        // Get the minimum value after popping
        System.out.println("Minimum value after pop: " + minStack.getMin()); // Should print 1
    }
}
