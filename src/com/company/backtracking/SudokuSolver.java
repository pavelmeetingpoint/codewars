package com.company.backtracking;

import java.util.*;

/**
 var array = new SudokuSolver(new int[][]{
 {0, 5, 7, 2, 0, 0, 0, 0, 0},
 {0, 0, 0, 0, 9, 0, 8, 1, 0},
 {0, 0, 0, 0, 0, 0, 0, 0, 0},
 {9, 0, 0, 0, 3, 0, 0, 0, 0},
 {0, 0, 0, 0, 0, 0, 0, 0, 7},
 {0, 8, 0, 0, 0, 0, 0, 0, 5},
 {0, 0, 0, 0, 0, 0, 0, 9, 4},
 {8, 0, 0, 0, 0, 0, 0, 3, 0},
 {0, 0, 2, 0, 0, 7, 0, 0, 0}});
 var result = array.solve();
 for(int i = 0; 9 > i; i++) {
 for (int j = 0; 9 > j; j++) {
 System.out.print(result[i][j] + " ");
 }
 System.out.println(" ");
 }
 */
public class SudokuSolver {

    private static final int SHAPE = 9;
    private static final int GRID = 3;
    private static final int EMPTY = 0;
    private static int[][] board;

    public SudokuSolver(int[][] grid) {
        this.board = grid;
    }

    public static int[][] solve() {
        validate(board);
        List<int[][]> solutions = new ArrayList<>();
        search(board, solutions);
        if(solutions.size() == 1){
            return solutions.get(0);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public static void validate(int[][] board){
        if (board.length != SHAPE) {
            throw new IllegalArgumentException();
        }
        for(int i = 0; SHAPE > i; i++) {
            if (board[i].length != SHAPE) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; SHAPE > j; j++) {
                if(board[i][j] < 0 || 9 < board[i][j]) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private static void search(int[][] board, List<int[][]> solutions){
        if(isStateValid(board)){
            solutions.add(copyBoard(board));
            if(solutions.size() > 1){
                throw new IllegalArgumentException();
            }
            return;
        }
        for(int i = 0; SHAPE > i; i++){
            for(int j = 0; SHAPE > j; j++){
                if(board[i][j] == EMPTY){
                    for(int candidate : getCandidates(board, i, j)){
                        board[i][j] = candidate;
                        search(board, solutions);
                        board[i][j] = EMPTY;
                    }
                    return;
                }
            }
        }
    }

    private static boolean isStateValid(int[][] board){
        return validateRows(board) && validateColums(board) && validateBoxes(board);
    }

    private static int[] getCandidates(int[][] board, int row, int column){
        int set = 0;
        set |= getRowDigits(board, row);
        set |= getColDigits(board, column);
        set |= getBoxDigits(board, row, column);
        return getNotFoundFromBitSet(set);
    }

    private static int[] getNotFoundFromBitSet(int set) {
        int count = SHAPE - Integer.bitCount(set);
        int[] result = new int[count];
        int index = 0;
        for (int i = 1; i <= SHAPE; i++) {
            if ((set & (1 << i)) == 0) {
                result[index++] = i;
            }
        }
        return result;
    }

    private static int getRowDigits(int[][] board, int row){
        int set = 0;
        for(int col = 0; board[row].length > col; col++){
            if (board[row][col] != EMPTY) {
                set |= 1 << board[row][col];
            }
        }
        return set;
    }

    private static int getColDigits(int[][] board, int col){
        int set = 0;
        for(int row = 0; board.length > row; row++){
            if (board[row][col] != EMPTY) {
                set |= 1 << board[row][col];
            }
        }
        return set;
    }

    private static int getBoxDigits(int[][] board, int row, int col){
        var set = 0;
        int boxStartRow = row - row % GRID;
        int boxStartCol = col - col % GRID;

        for (int i = boxStartRow; i < boxStartRow + GRID; i++) {
            for (int j = boxStartCol; j < boxStartCol + GRID; j++) {
                if (board[i][j] != EMPTY) {
                    set |= 1 << board[i][j];
                }
            }
        }
        return set;
    }

    private static boolean validateRows(int[][] board){
        for (int row = 0; SHAPE > row; row++) {
            int set = 0;
            for (int col = 0; SHAPE > col; col++) {
                int digit = board[row][col];
                if (digit == EMPTY) {
                    return false;
                }
                if ((set & (1 << digit)) != 0) {
                    return false;
                }
                set |= 1 << digit;
            }
        }
        return true;
    }

    private static boolean validateColums(int[][] board){
        for (int col = 0; SHAPE > col; col++) {
            var set = 0;
            for (int row = 0; SHAPE > row; row++) {
                var digit = board[row][col];
                if (digit == EMPTY) {
                    return false;
                }
                if ((set & (1 << digit)) != 0) {
                    return false;
                }
                set |= 1 << digit;
            }
        }
        return true;
    }

    private static boolean validateBoxes(int[][] board){
        for(int boxIndexRow = 0; SHAPE > boxIndexRow; boxIndexRow += GRID) {
            for (int boxIndexColumn = 0; SHAPE > boxIndexColumn; boxIndexColumn += GRID) {
                var set = 0;
                for (int row = boxIndexRow; GRID+boxIndexRow > row; row++) {
                    for (int col = boxIndexColumn; GRID + boxIndexColumn > col; col++) {
                        var digit = board[row][col];
                        if (digit == EMPTY) {
                            return false;
                        }
                        if ((set & (1 << digit)) != 0) {
                            return false;
                        }
                        set |= 1 << digit;
                    }
                }
            }
        }
        return true;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[SHAPE][SHAPE];
        for (int i = 0; SHAPE > i; i++) {
            for (int j = 0; SHAPE > j; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

}
