package com.company.array;

public class SumOfSubArray {

    public static SubArray findMaximumSubArray(int[] array, int low, int high){
        if(high == low){
            return new SubArray(low, high, array[low]);
        }
        int mid = (low + high) / 2;
        var left = findMaximumSubArray(array, low, mid);
        var right = findMaximumSubArray(array, mid + 1, high);
        var cross = findMaxCrossingSubArray(array, low, mid, high);
        if(left.getSum() > right.getSum() && left.getSum() > cross.getLeft()){
            return left;
        }
        else if(right.getSum() > left.getSum() && right.getSum() > cross.getSum()){
            return right;
        }
        else{
            return cross;
        }
    }

    private static SubArray findMaxCrossingSubArray(int[] array, int low, int mid, int high){
        int left_sum = Integer.MIN_VALUE;
        int sum = 0;
        int max_left = 0;
        for(int i = mid; i >= low; i--){
            sum += array[i];
            if(sum > left_sum){
                left_sum = sum;
                max_left = i;
            }
        }
        int right_sum = Integer.MIN_VALUE;
        sum = 0;
        int max_right = 0;
        for(int i = mid + 1; i <= high; i++){
            sum += array[i];
            if(sum > right_sum){
                right_sum = sum;
                max_right = i;
            }
        }
        return new SubArray(max_left, max_right, left_sum + right_sum);
    }

    private static class SubArray{
        private int left;
        private int right;
        private int sum;

        public SubArray(int left, int right, int sum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public int getSum() {
            return sum;
        }

        @Override
        public String toString() {
            return "SubArray{" +
                    "left=" + left +
                    ", right=" + right +
                    ", sum=" + sum +
                    '}';
        }
    }

}
