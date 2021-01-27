package javatest.sort;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 6, 7, 9};
        int[] temp = new int[arr.length];

        mergesort(arr, 0, arr.length - 1, temp);
        System.out.println(12);
    }

    public static void mergesort(int[] arr, int start, int end, int[] temp) {
        if (start == end){
            return;
        }

        mergesort(arr, start, (end - start) / 2, temp);
        mergesort(arr, (end - start) / 2 + 1, end, temp);
        merge(arr, start, (end - start) / 2, end, temp);

    }

    public static void merge(int[] arr, int start, int mid, int end, int[] tar) {
        int s1 = start, j = start, s2 = mid + 1;
        while (s1 < mid + 1 && s2 < end) {
            while (arr[s1] < arr[s2]) {
                tar[j++] = arr[s1++];
            }
            tar[j++] = arr[s2++];
        }
        while (s2 <= end) {
            tar[j++] = arr[s2++];
        }
        while (s1 <= mid) {
            tar[j++] = arr[s1++];
        }
    }

    public static void swap(int a[], int before, int after) {
        int temp = a[before];
        a[before] = a[after];
        a[after] = temp;
    }
}
