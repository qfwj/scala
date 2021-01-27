package javatest.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {7,4, 2, 9};
        bucketSort(arr,3);
        System.out.println(12);
    }

    public static void bucketSort(int[] arr, int num) {
        List<Integer>[] buck = new List[num];
        int max = arr[0],min= arr[0];
        for(int  i: arr){
            if(i> max) max= i;
            if(i< min) min= i;
        }
        int interval = (max -min)/num;
        for(int i : arr) {
           if(buck[ (i-min)/interval] == null)
               buck[(i-min)/interval] = new ArrayList<>();
            buck[(i-min)/interval].add(i);
        }
        for(List list : buck) {
            if(list != null)
            Collections.sort(list);
        }
        int m = 0;
        for(List<Integer> list : buck) {
            if(list != null)
           for(int i :list) {
               arr[m++] = i;
           }
        }


    }



    public static void swap(int a[], int before, int after) {
        int temp = a[before];
        a[before] = a[after];
        a[after] = temp;
    }
}
