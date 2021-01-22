package javatest.sort;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {11, 7, 8, 9};
        selectSort(arr);
        System.out.println(12);
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length == 1) return;

        for (int j = arr.length - 1; j >= 0 ; j--) {
            int max = j;
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[max]) {
                    max = i;
                }
            }
            swap(arr,j, max);


        }


    }


    public static void swap(int a[], int before, int after) {
        int temp = a[before];
        a[before] = a[after];
        a[after] = temp;
    }
}
