package javatest.sort;

/**
 * 快速排序的核心思想
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {8, 1, 2, 4, 5, 6, 7};
        quitSort(arr);
        System.out.println("dsd");
    }

    public static void quitSort(int[] arr) {
        if (arr == null || arr.length == 1) return;
        sort(arr, 0, arr.length - 1);
    }

    /**
     *  核心思路就是把mark作为一个特定的坑位(mark的值) 在 left、right做置换 移来移去
     * @param arr
     * @param left
     * @param right
     */
    public static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mark = arr[left];
        int sta = left;
        int en = right;
        while (left < right) {
            while (arr[right] >= mark && left < right)
                right--;
            arr[left] = arr[right];
            while (arr[left] < mark && left < right)
                left++;
            arr[right] = arr[left];
        }
        sort(arr, sta, left - 1);
        sort(arr, left + 1, en);
    }

    public static void swap(int a[], int before, int after) {
        int temp = a[before];
        a[before] = a[after];
        a[after] = temp;
    }
}
