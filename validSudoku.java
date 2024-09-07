import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class validSudoku {

// You are given a a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:

// Each row must contain the digits 1-9 without duplicates.
// Each column must contain the digits 1-9 without duplicates.
// Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
// Return true if the Sudoku board is valid, otherwise return false

// Note: A board does not need to be full or be solvable to be valid.

public static boolean isValidSudokuUsingHashMap(char[][] board) {
        
    // HashMap -> key (Integer): row/column/sub_box, value(HashSet:- to keep track of unique values): cell value in row/column/sub_box

    // for unique value in rows
    Map<Integer, Set<Character>> rows = new HashMap<>();
    // for unique value in columns
    Map<Integer, Set<Character>> cols = new HashMap<>();
    // for unique value in sub boxes
    Map<Integer, Set<Character>> sub_box = new HashMap<>();

    // iterating the board
    for (int r=0; r<9; r++) {
        for (int c=0; c<9; c++) {
            // keeping track of the value in that particular row/column/sub_box
            char cell_value = board[r][c];
            // skip if empty/ consider the non empty ones
            if (cell_value != '.') {
                // check if any of the row, col, sub_contains the current cell value
                // new HashSet<>() to avoid null pointer exception (does not store it to Map)
                // if any repeated values occurred, then return false
                // for ex: let's say row 0  and col 0 has 1 (& sub_box 0), in next iteration we have 1 in row 0 and col 1 (& sub_box 0) it will return false as row 0 already contains 1
                if (rows.getOrDefault(r, new HashSet<>()).contains(cell_value) || 
                    cols.getOrDefault(c, new HashSet<>()).contains(cell_value) ||
                    // (r/3)*3+c/3 to get only 0, 1, 2 instead of (0, 0), (0, 1) ... , (2, 0) etc
                    // as long as sub_box contains all unique numbers (i.e., 1-9) then no worries
                    sub_box.getOrDefault((r/3)*3+c/3, new HashSet<>()).contains(cell_value)
                ) {
                    return false;
                }

                // insertion mechanism
                // k -> function (remapping function)
                // computeIfAbsent inserts value directly to the map created at the start
                rows.computeIfAbsent(r, k -> new HashSet<>()).add(cell_value);
                cols.computeIfAbsent(c, k -> new HashSet<>()).add(cell_value);
                sub_box.computeIfAbsent((r/3)*3+c/3, k -> new HashSet<>()).add(cell_value);
            }
        }
    }
    return true;
}

    public static boolean isValidSudokuUsingHashSet(char[][] board) {
        
        // keeping track of visited cells with the help of row number, column number and sub box number
        HashSet<String> visited = new HashSet<>();

        // to iterate over the board
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                // keep track of current element in the board
                char currentElement = board[i][j];
                // skip the empty cells
                if (board[i][j] != '.') {
                    // HashSet's .add() method return true if the element is not present in the HashSet
                    // so to obtain it's opposite, i/e., if the element is present (already inserted that number before), negate the .add() method

                    // 1 found in row 0 (inserted as such in HashSet)
                    // if the same sentence is found again then .add() method returns false as the value is already present
                    // same for other current elements too 
                    if (!visited.add(currentElement + " found in row " + i ) || 
                        // 1 found in column 0
                        !visited.add(currentElement + " found in column " + j) || 
                        // 1 found in sub box 0/3-0/3 = 0-0
                        !visited.add(currentElement + " found in sub box " + i/3 + "-" + j/3)) {
                        
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = 
        {{'1','2','.','.','3','.','.','.','.'},
        {'4','.','.','5','.','.','.','.','.'},
        {'.','9','8','.','.','.','.','.','3'},
        {'5','.','.','.','6','.','.','.','4'},
        {'.','.','.','8','.','3','.','.','5'},
        {'7','.','.','.','2','.','.','.','6'},
        {'.','.','.','.','.','.','2','.','.'},
        {'.','.','.','4','1','9','.','.','8'},
        {'.','.','.','.','8','.','.','7','9'}};

        System.out.println(isValidSudokuUsingHashSet
        (board));

        System.out.println(isValidSudokuUsingHashMap(board));
    }
}
