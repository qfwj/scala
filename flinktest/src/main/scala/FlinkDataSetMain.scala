import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.java.aggregation.Aggregations
import org.apache.flink.api.scala._
import org.apache.flink.util.Collector
import org.apache.flink.api.scala._
/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/7/31 17:54
  */
object FlinkDataSetMain {


  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment



    /*join*/
    val textJoin11 = env.fromElements(("1", 2, 2), ("12", 1, 5), ("21", 5, 1), ("22", 3, 2))
    val textJoin21 = env.fromElements((44, 44, "mmm"), (2, 2, "asdas"), (2, 1, "hhh"), (1, 5, "mn"), (2, 1, "ssss"), (3, 2, "ddd"))

    /*join strategy  hint  BROADCAST_HASH_FIRST  REPARTITION_HASH_SECOND*/

    /*right join  保证右边一定有*/
    textJoin11.rightOuterJoin(textJoin21).where(1).equalTo(0) {
      (p1, p2) => ( {
        if (p1 == null) "" else p1._1
      } + p2._3, if (p1 == null) 0 else p1._2 + p2._1)
    }.print()


    /*left join  保证左边一定有*/
    /*(22ddd,6)
      (12mn,2)
      (21,5)
      (1asdas,4)
      (1hhh,4)
      (1ssss,4)*/
    textJoin11.leftOuterJoin(textJoin21).where(1).equalTo(0) {
      (p1, p2) => (p1._1 + {
        if (p2 == null) "" else p2._3
      }, p1._2 + {
        if (p2 == null) 0 else p2._1
      })
    }.print()





    /*支持额外函数*/
    textJoin11.join(textJoin21).where(1).equalTo(0) {
      (p1, p2) => (p1._1 + p2._3, p1._2 + p2._1)
    }.print()



    /*
      * ((2,3,2),(3,2,ddd))
      * ((1,1,5),(1,5,mn))
      * ((1,2,2),(2,2,asdas))
      * ((1,2,2),(2,1,hhh))
      * */
    textJoin11.join(textJoin21).where(1).equalTo(0).print()

























    val input: DataSet[String] = env.fromElements("A", "B", "C", "D", "E", "F", "G", "H")



    /*delta  iterator */
    val initialSolutionSet: DataSet[(Long, Double)] = env.fromElements((1,2.0),(2,1.0))

    val initialWorkset: DataSet[(Long, Double)] = env.fromElements((1,2.0),(2,1.0))

    val maxIterations = 100
    val keyPosition = 0

/*    val result = initialSolutionSet.iterateDelta(initialWorkset, maxIterations, Array(keyPosition)) {
      (solution, workset) =>
        val candidateUpdates = workset.groupBy(1).reduceGroup{(iter) => {
          iter.map()
        }}
        val deltas = candidateUpdates.join(solution).where(0).equalTo(0)

        val nextWorkset = deltas.filter(new FilterByThreshold())

        (deltas, nextWorkset)
    }

    env.execute()*/


    /*iterator */
    val iterable = env.fromCollection(List(1,2,3,4))
    iterable.iterate(3){
      input:DataSet[Int] =>
        input.map(_+1)
    }.print()




    val text = env.fromElements("1", "2", "3", "3", "3", "2")

    val textCo = env.fromElements("4", "5", "6")

    val textPrintCo = textCo //.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map {
      (_, 1)
    }
    val textPrint = text //.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map {
      (_, 1)
    }
    /*CoGroup*/
    val textCogroup1: DataSet[(String, Int)] = env.fromElements(("11", 2), ("12", 1), ("13", 5), ("14", 3))
    val textCogroup2: DataSet[(String, Int)] = env.fromElements(("21", 2), ("22", 1), ("23", 5), ("24", 3))
   // textCogroup1.coGroup(textCogroup2).where(1).equalTo(1)
  //  {
    //  (p1, p2, out:Collector[Int]) => {
      //  p1.foreach(f=>out.collect(1))
        /*for(f <- p1)
          println(f)

        for(f <- p2)
          println(f)
*/
        //out.collect(1)
    //  }
   // }.print()


    /*Cross input1 和 input2 每项进行组合 产生结果count为 1.count * 2.count*/
    val textCross1 = env.fromElements(("11", 2, 2), ("112", 2, 2) ,("12", 1, 5), ("13", 5, 1), ("14", 3, 2))
    val textCross2 = env.fromElements(("21", 2, 2),("212", 2, 2), ("22", 1, 5), ("23", 5, 1), ("24", 3, 2))
    /* crossWithTiny
     crossWithHuge*/
    textCross1.cross(textCross2) {
      (p1, p2) => {
        (p1._1, p2._1)
      }
    }.print()


    /*join*/
    val textJoin1 = env.fromElements(("1", 2, 2), ("12", 1, 5), ("21", 5, 1), ("22", 3, 2))
    val textJoin2 = env.fromElements((2, 2, "asdas"), (2, 1, "hhh"), (1, 5, "mn"), (2, 1, "ssss"), (3, 2, "ddd"))

    /*join strategy  hint  BROADCAST_HASH_FIRST  REPARTITION_HASH_SECOND*/

    /*right join  保证右边一定有*/
    textJoin1.rightOuterJoin(textJoin2).where(1).equalTo(0) {
      (p1, p2) => ( {
        if (p1 == null) "" else p1._1
      } + p2._3, if (p1 == null) 0 else p1._2 + p2._1)
    }.print()


    /*left join  保证左边一定有*/
    /*(22ddd,6)
      (12mn,2)
      (21,5)
      (1asdas,4)
      (1hhh,4)
      (1ssss,4)*/
    textJoin1.leftOuterJoin(textJoin2).where(1).equalTo(0) {
      (p1, p2) => (p1._1 + {
        if (p2 == null) "" else p2._3
      }, p1._2 + {
        if (p2 == null) 0 else p2._1
      })
    }.print()





    /*支持额外函数*/
    textJoin1.join(textJoin2).where(1).equalTo(0) {
      (p1, p2) => (p1._1 + p2._3, p1._2 + p2._1)
    }.print()



    /*
      * ((2,3,2),(3,2,ddd))
      * ((1,1,5),(1,5,mn))
      * ((1,2,2),(2,2,asdas))
      * ((1,2,2),(2,1,hhh))
      * */
    textJoin1.join(textJoin2).where(1).equalTo(0).print()


    /*Distinct */
    val textDistin = env.fromElements(("1", 2, 2), ("1", 2, 1), ("1", 1, 5), ("2", 2, 1), ("2", 3, 2))
    textDistin.distinct(0, 1).print()

    /*Aggregate 分为full dataSet 和 group data*/
    val combineAggra = env.fromElements(("1", 2, 2), ("1", 2, 1), ("1", 1, 5), ("2", 2, 1), ("2", 3, 2))
    combineAggra.groupBy(0).maxBy(2, 1).print()
    //.maxBy(2).print()
    //.max(1).print()


    /*输出 (2,5,1)  (1,4,1)*/
    combineAggra.groupBy(0).aggregate(Aggregations.SUM, 1).and(Aggregations.MIN, 2).print()
    /*输出 (2,5,2)  (1,4,5)*/
    combineAggra.groupBy(0).aggregate(Aggregations.SUM, 1).and(Aggregations.MAX, 2).print()
    /*输出 (2,5,3)  (1,4,8)*/
    combineAggra.groupBy(0).aggregate(Aggregations.SUM, 1).andSum(2).print()


    /*combineGroup  还有问题*/
    val combineGroup = env.fromElements(("1", 2), ("1", 1), ("1", 5), ("2", 1), ("2", 2))
    //    combineGroup.groupBy(0).combineGroup {
    //      (words, out: Collector[(String, Int)]) =>
    //        var key: String = null
    //        var sum = 0
    //        for ((word, count) <- words) {
    //          key = word
    //          sum = sum +  count
    //        }
    //        out.collect((key, sum))
    //    }.print()

    /*sortGroup*/
    val sortedGroupin = env.fromElements(("1", 2), ("1", 1), ("1", 5), ("2", 1), ("2", 2))
    sortedGroupin.groupBy(0)
      .sortGroup(1, Order.ASCENDING)
      .first(3).print()

    /*groupReduce  对每个group后的list做reduce操作*/
    text.mapPartition { in => in.map((_, 1)) }.groupBy(0)
      .reduceGroup(in => in.reduce((w1, w2) => (w1._1, w1._2 + w2._2))).print()



    /*
    map groupby  reduce
    (3,3)
    (2,2)
    (1,1)*/
    text.mapPartition { in => in.map((_, 1)) }.groupBy(1).reduce((w1, w2) => (w1._1 + w2._1, w1._2 + w2._2))
      .print()

    textPrint.groupBy(0)

    text.union(textCo).print() //联合成一个dataSet
    textPrintCo.cross(textPrint).print() //组合每个第一个DataSet 和 第二个dataSet 成一个Tuple2的dataSet
    textPrint.count() //总数
    textPrint.distinct(_._2).print() //根据某个字段进行去重 distinct(0) distinct("field name")
    val co = textPrint.coGroup(textPrintCo).where(0).equalTo(1)
    // .groupBy(0)
    // .sum(1)

    textPrint.print()

    // env.execute()
    println("")

  }

}
