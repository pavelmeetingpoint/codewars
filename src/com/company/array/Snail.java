package com.company.array;

/*
*         int[][] array
                = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        for(int i : snail(array)){
            System.out.println(i);
        }
        *
        * */
public class Snail {

    public static int[] snail(int[][] array) {
        if(array.length == 1 && array[0].length == 0){
            return new int[0];
        }
        var size = array.length;
        int[] result = new int[size*size];
        boolean[][] pass = new boolean[size][size];
        int index = 1;
        var direction = Direction.RIGHT;
        init(direction, result, pass, array);
        while(index < size*size){
            direction = getDirection(pass, size, direction.rowIndex, direction.columnIndex);
            result[index] = array[direction.getRowIndex()][direction.getColumnIndex()];
            index++;
        }
        return result;
    }

    public static void init(Direction direction, int[] result, boolean[][] pass, int[][] array){
        direction.setColumnIndex(0);
        direction.setRowIndex(0);
        result[0] = array[0][0];
        pass[0][0] = true;
    }

    public static Direction getDirection(boolean[][] pass, int n, int currentRow, int currentColumn) {
        if(currentColumn + 1 < n && !pass[currentRow][currentColumn + 1] && (currentRow - 1 < 0 || pass[currentRow-1][currentColumn])){
            var direction = Direction.RIGHT;
            direction.setRowIndex(currentRow);
            direction.setColumnIndex(currentColumn + 1);
            pass[currentRow][currentColumn + 1] = true;
            return direction;
        }
        else if(currentRow + 1 < n && !pass[currentRow+1][currentColumn]){
            var direction = Direction.DOWN;
            direction.setRowIndex(currentRow + 1);
            direction.setColumnIndex(currentColumn);
            pass[currentRow+1][currentColumn] = true;
            return direction;
        }
        else if(currentColumn - 1 >= 0 && !pass[currentRow][currentColumn - 1]){
            var direction = Direction.LEFT;
            direction.setRowIndex(currentRow);
            direction.setColumnIndex(currentColumn - 1);
            pass[currentRow][currentColumn - 1] = true;
            return direction;
        }
        else{
            var direction = Direction.UP;
            direction.setRowIndex(currentRow - 1);
            direction.setColumnIndex(currentColumn);
            pass[currentRow-1][currentColumn] = true;
            return direction;
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        private int rowIndex;
        private int columnIndex;

        public int getRowIndex() {
            return rowIndex;
        }

        public void setRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        public int getColumnIndex() {
            return columnIndex;
        }

        public void setColumnIndex(int columnIndex) {
            this.columnIndex = columnIndex;
        }
    }

}
