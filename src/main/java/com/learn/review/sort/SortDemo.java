package com.learn.review.sort;

/**
 * @Author: Kelo
 * @Date: 2022/9/20
 */
public class SortDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{6, 1, 10, 5, 9, 18, 90, 28, 67, 12, 7};

//        Sort.bubbleSort(arr);
//        print(arr);

        arr = Sort.mergeSort(arr);
        print(arr);

    }

    private static void print(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
