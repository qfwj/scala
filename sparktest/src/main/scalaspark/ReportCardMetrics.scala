package scalaspark

import org.apache.spark.rdd.RDD

case class ReportCardMetrics(longestWord: Int,
                             happyMentions: Int,
                             averageWords: Double) {

  def calculateReportCardStatistics(rdd: RDD[(String, String)]
                                   ): RDD[(String, ReportCardMetrics)] = {
    rdd.aggregateByKey(new MetricsCalculator(totalWords = 0, longestWord = 0,
      happyMentions = 0, numberReportCards = 0))(
      seqOp = ((reportCardMetrics, reportCardText) => reportCardMetrics.sequenceOp(reportCardText)),
      combOp = (x, y) => x.compOp(y)).mapValues(_.toReportCardMetrics)

  }

}