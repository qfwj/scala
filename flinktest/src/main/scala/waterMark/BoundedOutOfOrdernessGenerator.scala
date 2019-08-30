package waterMark

import kafka.User
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.watermark.Watermark

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/8/30 16:15
  */
class BoundedOutOfOrdernessGenerator extends AssignerWithPeriodicWatermarks[User] {

  val maxOutOfOrderness = 3500L // 3.5 seconds

  var currentMaxTimestamp: Long = _

  override def extractTimestamp(element: User, previousElementTimestamp: Long): Long = {
    val timestamp:Long = element.createTime
    currentMaxTimestamp = if (timestamp > previousElementTimestamp) timestamp else previousElementTimestamp
    timestamp
  }

  override def getCurrentWatermark(): Watermark = {
    // return the watermark as current highest timestamp minus the out-of-orderness bound
    new Watermark(125)
  }
}

/**
  * This generator generates watermarks that are lagging behind processing time by a fixed amount.
  * It assumes that elements arrive in Flink after a bounded delay.
  */
class TimeLagWatermarkGenerator extends AssignerWithPeriodicWatermarks[User] {

  val maxTimeLag = 10000L // 5 seconds
  var currentMaxTimestamp: Long = _

  override def extractTimestamp(element: User, previousElementTimestamp: Long): Long = {
    currentMaxTimestamp = element.createTime
    element.createTime
  }

  override def getCurrentWatermark(): Watermark = {
    // return the watermark as current time minus the maximum time lag
    val tmp = System.currentTimeMillis() - maxTimeLag

    new Watermark(tmp)
  }
}