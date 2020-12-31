package com.qf.algorithm

/**
 * description:  <br>
 * date: 2020/12/31 15:19 <br>
 * author: zhbo <br>
 */
object HeapSortMain extends App {
  val arr: Array[Int] = Array(6, 4, 2, 3)
  HeapSort.heapSort(arr)
  println(arr)
}

object HeapSort {
  def heapSort(arr: Array[Int]) = {
    val len = arr.length -1
    var len2 = len / 2
    while (len2 >= 0) {
      var ini: Int = len2
      while (ini > 0) {
        if (arr(ini * 2) < arr(ini)) {
          val temp = arr(ini)
          arr(ini) = arr(ini * 2)
          arr(ini * 2) = temp
        }
        if (arr(ini * 2 -1) < arr(ini)) {
          val temp = arr(ini)
          arr(ini) = arr(ini * 2-1)
          arr(ini * 2-1) = temp
        }
        ini /= 2
      }
      len2 -= 1
    }


  }

}