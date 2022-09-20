package com.learn.review.sort;

import com.learn.review.datastructure.array.Array;

import java.util.Arrays;

/**
 * @Author: Kelo
 * @Date: 2022/9/20
 */
public class Sort {

    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] >= arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static int[] mergeSort(int[] arr) {

        if (arr.length < 2) {
            return arr;
        }
        // 从中间劈半
        int middle = (int) Math.floor(arr.length/2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {

    }

}
