import scala.math.Ordering
import scala.util.Sorting


object MainScala {

  def main(args: Array[String]): Unit = {



    val list = List("1-2","2-3","1-4")

    implicit val my_self_Ordering = new Ordering[String] {
      override def compare(a: String, b: String): Int = {
        val a_b: Array[String] = a.split("-")
         val a_1 = a_b(0).toInt
         val a_2 = a_b(1).toInt
         val b_b = b.split("-")
         val b_1 = b_b(0).toInt
         val b_2 = b_b(1).toInt
         if (a_1 == b_1) {
           b_2 - a_2
         } else {
            b_1 -a_1
         }

      }
    }
    list.sorted.foreach(println) //2-3 1-4 1-2




















    var data111  =  Array((1,2),(2,3),(3,4),(3,1),(0,2))
    Sorting.quickSort(data111)(Ordering[(Int,Int)].on(f=>(f._1 * -1,f._2 )))

    Sorting.quickSort(data111)(new Ordering[(Int, Int)] {
      override def compare(x: (Int, Int), y: (Int, Int)): Int = {
        if(x._1 == y._1)
          y._2 - x._2
        else
          x._1 -y._1
      }
    })


    for (elem <- data111) {
      print(elem) //(3,1)(3,4)(2,3)(1,2)(0,2)
    }
    println()
    Sorting.quickSort(data111)(Ordering.by[(Int,Int),(Int,Int)] (f => (f._1* -1, f._2)))

    for (elem <- data111) {
      print(elem) //(3,1)(3,4)(2,3)(1,2)(0,2)
    }
  }
}
