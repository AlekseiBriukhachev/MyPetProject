package com.aleksei.app;

import java.util.Arrays;

public class CountingSorting {
    public static void main(String[] args) {
        int n = (int) (5 + Math.random() * 10);
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 20);
        }
        System.out.println("Array: " + Arrays.toString(array));
        counting_sort(array);
        int[] copyArr = Arrays.copyOf(array, n);
        System.out.println("Array copy: " + Arrays.toString(copyArr));
        System.out.println(Arrays.equals(array, copyArr));
    }

    private static void counting_sort(int[] array) {
        int size = array.length;
        int[] output = new int[size + 1];
        // Find the maximum value in the array
        int max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max)
                max = array[i];
        }
        // Create count array and fill it with zeros
        int[] count = new int[max + 1];
        for (int i = 0; i < max; ++i) {
            count[i] = 0;
        }
        // Find and store the count of each element
        for (int i = 0; i < size; i++) {
            count[array[i]]++;
        }
        // Calculate and store cummulative sum of elements of count array
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        // Traverse the initial array from the end and place the elements
        // in output array into their respective positions
        for (int i = size - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }
        // Copy the sorted elements into the initial array
        for (int i = 0; i < size; i++) {
            array[i] = output[i];
        }
        System.out.println("NewArray: " + Arrays.toString(array));
    }
}

