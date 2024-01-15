package com.company.binaryApproach;

public class MaxSumOfKBlocks {

    public int solution(int k, int m, int[] a) {
        int begin = m;
        int end = getSumOfAllElements(a);
        int sum = 0;
        while(begin <= end){
            int mid = (begin + end) / 2;
            if(isPossibleToDivide(mid, k, a)){
                end = mid - 1;
                sum = mid;
            }
            else{
                begin = mid + 1;
            }
        }
        return sum;
    }

    private int getSumOfAllElements(int[] a){
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum = sum + a[i];
        }
        return sum;
    }

    private boolean isPossibleToDivide(int mid, int k, int[] a){
        int blocks = 0;
        int currentSum = 0;

        for (int i = 0; i < a.length; i++) {
            if (currentSum + a[i] > mid) {
                blocks++;
                currentSum = a[i];
            } else {
                currentSum += a[i];
            }

            if (blocks >= k) {
                return true;
            }
        }

        return false;
    }

}
