package javatest.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {11, 7, 8, 9};
        bubbleSort(arr);
        System.out.println(12);
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 1) return;
        boolean swap = true;
        for (int j = arr.length - 1; j >= 0 && swap; j--) {
            swap = false;
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap = true;
                    swap(arr, i, i + 1);
                }
            }
        }


    }


    public static void swap(int a[], int before, int after) {
        int temp = a[before];
        a[before] = a[after];
        a[after] = temp;
    }
}
