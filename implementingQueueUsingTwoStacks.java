import java.util.Stack;

public class implementingQueueUsingTwoStacks {

    static Stack<Integer> first = new Stack<>();
    static Stack<Integer> second = new Stack<>();

    public static boolean isEmpty() {
        return first.isEmpty();
    }

    // T.C of add is O(1) and remove and peek is O(n)
    public static void add (int item) {
        first.push(item);
    }

    // move from the first stack to the second (helper) stack, so that we can have elements inserted at first to be at the top since QUEUE's principle is "First In First Out" 
    public static int remove () {
        // pop from first stack and insert it to second stack
        while (!first.isEmpty()) {
            second.push(first.pop());
        }

        // remove the element from second stack and keep it in a variable
        int removed = second.pop();

        // push back elements from first stack to second after removing
        while (!second.isEmpty()) {
            first.push(second.pop());
        }

        return removed;
    }

    // same as remove() for peek
    public static int peek() {
        while (!first.isEmpty()) {
            second.push(first.pop());
        }

        int peeked = second.peek();

        while (!second.isEmpty()) {
            first.push(second.pop());
        }

        return peeked;
    }

    // T.c of add is O(n) and remove and peek is O(1)
    public static void addLessEfficient (int item) {

        // remove the items first and put it in second and after inserting new element in first, insert all the elements from second to first that way in first the elements will be store in reverse order

        // first empty; second(1, 2, 3, 4); insert 5; first(5); first(second(4, 3, 2, 1)) 

        // put the elements of first stack into second
        while (!first.isEmpty()) {
            second.push(first.pop());
        }
        // enter the first item in first stack
        first.push(item);

        // put the elements of second stack into first
        while (!second.isEmpty()) {
            first.push(second.pop());
        }
    } 

    public static int removeEfficient () {
        return first.pop();
    }    

    public static int peekEfficient() {
        return first.peek();
    }

    public static void main(String[] args) {
        add(10);
        add(20);
        add(30);

        System.out.println("Peek after adding 10, 20, 30: " + peek()); // Should print 10

        System.out.println("Remove: " + remove()); // Should remove and print 10
        System.out.println("Peek after removing 10: " + peek()); // Should print 20

        System.out.println("Remove: " + remove()); // Should remove and print 20
        System.out.println("Remove: " + remove()); // Should remove and print 30

        // Test if queue is empty
        System.out.println("Is queue empty? " + isEmpty()); // Should print true

        // Test addLessEfficient and removeEfficient methods
        addLessEfficient(40);
        addLessEfficient(50);

        System.out.println("Peek after adding 40, 50: " + peekEfficient()); // Should print 40
        System.out.println("Remove: " + removeEfficient()); // Should remove and print 40
        System.out.println("Peek after removing 40: " + peekEfficient()); // Should print 50
        System.out.println("Remove: " + removeEfficient()); // Should remove and print 50

        System.out.println("Is queue empty? " + isEmpty()); // Should print true
    }
}
