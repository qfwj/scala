
class MetricsCalculator(
                         val totalWords : Int,
                         val longestWord: Int,
                         val happyMentions : Int,
                         val numberReportCards: Int) extends Serializable {
  def sequenceOp(reportCardContent : String) : MetricsCalculator = {
    val words = reportCardContent.split(" ")
    val tW = words.length
    val lW = words.map( w => w.length).max
    val hM = words.count(w => w.toLowerCase.equals("happy"))
    new MetricsCalculator(
      tW + totalWords,
      Math.max(longestWord, lW),
      hM + happyMentions,
      numberReportCards + 1)
  }
  def compOp(other : MetricsCalculator) : MetricsCalculator = {
    new MetricsCalculator(
      this.totalWords + other.totalWords,
      Math.max(this.longestWord, other.longestWord),
      this.happyMentions + other.happyMentions,
      this.numberReportCards + other.numberReportCards)
  }
  def toReportCardMetrics =
    ReportCardMetrics(
      longestWord,
      happyMentions,
      totalWords.toDouble/numberReportCards)
}