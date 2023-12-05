package com.company.sorting;


/**
 * int[] array = new int[] { 1, 2, 13, 4, 5, 6, 7, 8 };
 *         mergeSort(array, 0, array.length - 1);
 *         for(int i : array){
 *             System.out.println(i);
 *         }
 */
public class MergeSort {

    public static void mergeSort(int[] array, int start, int end){
        if(start < end){
            int middle = (end + start) / 2;
            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, end);
            arrayMerge(array, start, middle, end);
        }
    }

    public static void arrayMerge(int[] array, int start, int middle, int end){
        int[] left = new int[middle - start + 1];
        int[] right = new int[end - middle + 1];
        int index = 0;
        for(int i = start; i <= middle; i++){
            left[index] = array[i];
            index++;
        }
        index = 0;
        for(int i = middle + 1; i <= end; i++){
            right[index] = array[i];
            index++;
        }
        var i = 0;
        var j = 0;
        for(index = start; index <= end; index++){
            if(i < left.length && left[i] > right[j]){
                array[index] = left[i];
                i++;
            }
            else{
                array[index] = right[j];
                j++;
            }
        }
    }

}
