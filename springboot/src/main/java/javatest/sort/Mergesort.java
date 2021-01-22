package javatest.sort;

public class Mergesort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 6, 7, 9};
        mergesort(arr, 0, arr.length - 1);
        System.out.println(12);
    }

    public static void mergesort(int[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        mergesort(arr, start, (end + start) / 2);
        mergesort(arr, (end + start) / 2 + 1, end);
        merge(arr, start, (end + start) / 2, end);

    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int[] tar = new int[end - start + 1];
        int s1 = start, j = 0, s2 = mid + 1;
        while (s1 <= mid && s2 <= end) {
            if (arr[s1] <= arr[s2]) {
                tar[j++] = arr[s1++];
            } else {
                tar[j++] = arr[s2++];
            }
        }
        while (s2 <= end) {
            tar[j++] = arr[s2++];
        }
        while (s1 <= mid) {
            tar[j++] = arr[s1++];
        }
        for (int t = 0; t < tar.length; t++) {
            arr[start + t] = tar[t];
        }
    }

}
