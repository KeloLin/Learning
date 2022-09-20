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
     *
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
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        // 合并在一起的大小
        int[] result = new int[left.length + right.length];
        // 小的在左，大的在右
        int i = 0;
        // 左边的第一个比右边的第一个小，就取小的那个，剩余的继续比较，直到全部比较完成
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
            i++;
        }
        // 如果左右两个数组还有存在数据的，则追加到最后
        if (left.length > 0) {
            for (int j = 0; j < left.length; j++) {
                result[i] = left[j];
                i++;
            }
        }

        if (right.length > 0) {
            for (int j = 0; j < right.length; j++) {
                result[i] = right[j];
                i++;
            }
        }
        return result;
    }

}
