import java.util.Arrays;

/* Alexa has two stacks of non-negative integers, stack a and stack b where index 0 denotes the top of the stack. Alexa challenges Nick to play the following game:

In each move, Nick can remove one integer from the top of either stack a or stack b.
Nick keeps a running sum of the integers he removes from the two stacks.
Nick is disqualified from the game if, at any point, his running sum becomes greater than some integer maxSum given at the beginning of the game.
Nick's final score is the total number of integers he has removed from the two stacks.
Given a, b, and maxSum for g games, find the maximum possible score Nick can achieve.

Example
The maximum number of values Nick can remove is 4. There are two sets of choices with this result.

Remove 1, 2, 3, 4 from a with sum of 10.
Remove 1, 2, 3 from a and 6 from b with sum of 12.
Function Description
Complete the twoStacks function in the editor below.

twoStacks has the following parameters: - int maxSum: the maximum allowed sum (here 10)
- int a[n]: the first stack
- int b[m]: the second stack

Returns
- int: the maximum number of selections Nick can make

Input 1:
10 -> sum
4 2 4 6 1
2 1 8 5

Output 1: 4

Explanation: can remove 4, 2, 4 from a (sum=10, moves=3), can remove 4, 2 from a and 2, 1 from b (sum=9, moves=4); maxMoves = 4
*/

public class gameOfTwoStacks {

    static int twoStacks(int maxSum, int[] a, int[] b) {
        // -1 because we go till the step that exceeds the maxSum, so -1 to consider only previous step
        // dry run: twoStacks(10, {4, 2, 4, 6, 1}, {2, 1, 8, 5}, 0, 0)
        return twoStacks(maxSum, a, b, 0, 0) - 1;
    }

    private static int twoStacks(int maxSum, int[] a, int[] b, int sum, int count) {
        // Base case: If the current sum exceeds targetSum, return the number of valid moves (count)
        if (sum > maxSum) {
            return count;
        }

        // If one of the stacks is empty, return the current count.
        if (a.length == 0 || b.length == 0) {
            return count;
        }

        // Recursive case 1: Remove the top element from stack 'a'.
        // dry run: twoStacks(10, {2, 4, 6, 1}, {2, 1, 8, 5}, 4, 1) // remove 4 from stack a
        // dry run: twoStacks(10, {4, 6, 1}, {2, 1, 8, 5}, 6, 2) // remove 2 from stack a
        // dry run: twoStacks(10, {6, 1}, {2, 1, 8, 5}, 10, 3) // remove 4 from stack a
        // dry run: twoStacks(10, {1}, {2, 1, 8, 5}, 16, 4) // Exceeds maxSum, returns 3
        int ans1 = twoStacks(maxSum, Arrays.copyOfRange(a, 1, a.length), b, sum + a[0], count + 1);

        // Recursive case 2: Remove the top element from stack 'b'.
        // dry run: twoStacks(10, {4, 6, 1}, {1, 8, 5}, 8, 3)
        // dry run: twoStacks(10, {4, 6, 1}, {8, 5}, 9, 4) 
        // dry run: twoStacks(10, {4, 6, 1}, {5}, 17, 5) // Exceeds maxSum, returns 4
        int ans2 = twoStacks(maxSum, a, Arrays.copyOfRange(b, 1, b.length), sum + b[0], count + 1);

         // Return the maximum of the two possible results.
        return Math.max(ans1, ans2); // 4

    }

    public static void main(String[] args) {
        // 4 being the top most element (i.e., inserted at last)
        int[] arr1 = {4, 2, 4, 6, 1};
        // 2 being the top most element (i.e., inserted at last)
        int[] arr2 = {12, 1, 8, 5};

        int maxSum = 10;

        System.out.println(twoStacks(maxSum, arr1, arr2));
    }
}
