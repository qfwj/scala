package scalaspark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/5/1017:07
  */



/*
*
*
* kafka.producer.brokerList=10.2.113.27:9092,10.2.113.28:9092,10.2.113.29:9092
#cleanAudit已作废
kafka.topic.message.find=newsAudit
kafka.topic.message.special=auditFirst
kafka.topic.message.video=videoAudit
kafka.topic.message.opencms=topic_opencms_audit_message
kafka.topic.message.thumbnail=topic_video_thumbnail
kafka.topic.message.rank=topic_message_rank
kafka.topic.message.recommend=topic_message_recommend
kafka.topic.comment.dynamic=topic_audit_comment_source
kafka.topic.content.relation=topic_content_relation
kafka.topic.user.meta=topic_audit_user_meta
kafka.topic.message.delivery=newsDelivery
kafka.consumer.group.id=capricorn-audit-server6
#����ר��topic
kafka.topic.message.theme=topic_subject_audit
#ϴ����Ϣ
kafka.topic.wash.to.audit=washToAudit
#已作废
kafka.topic.xmwb.to.audit=audit_xinminwanbao

*
*
*
*
*
*
*
*
* */
object StreamMain {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[5]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(1))
    val stream = ssc.textFileStream("C:\\Users\\nefu_\\Desktop\\ETL")
    //stream.map(f=>(f,f)).repartition()reforeachRDD(_.collect().foreach(println(_)))


    ssc.start()
    ssc.awaitTermination()
    println("")

  }
}
