package waterMark

import kafka.User
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks
import org.apache.flink.streaming.api.watermark.Watermark

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/9/2 16:37
  */
class PunctuatedAssigner extends AssignerWithPunctuatedWatermarks[User]{


  @Override
  def extractTimestamp( element:User,  previousElementTimestamp:Long):Long ={
    element.createTime
  }
  @Override
   def checkAndGetNextWatermark(lastElement: User, extractedTimestamp: Long): Watermark =  {
     if(lastElement.name.equals("小明"))
     new Watermark(extractedTimestamp -3000)
     else null
  }
}
