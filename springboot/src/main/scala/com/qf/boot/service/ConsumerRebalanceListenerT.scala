package com.qf.boot.service

import java.util
import java.util.Collection

import org.apache.kafka.clients.consumer.{ConsumerRebalanceListener, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

/**
 * description:  <br>
 * date: 2021/1/4 10:43 <br>
 * author: zhbo <br>
 */
class ConsumerRebalanceListenerT(val consumer: KafkaConsumer[String, String])  extends  ConsumerRebalanceListener{
 def onPartitionsAssigned( partitions: util.Collection[TopicPartition]) = {
   consumer.seek(new TopicPartition("testkafka", 1), 2555)
   consumer.seek(new TopicPartition("testkafka", 0), 2935)
   consumer.seek(new TopicPartition("testkafka", 2), 2400)
 }

 override def onPartitionsLost(partitions: util.Collection[TopicPartition]) = {
   //consumer.seek(new TopicPartition("testkafka", 1), 2555)

 }
  def onPartitionsRevoked( partitions: util.Collection[TopicPartition]) = {
    //consumer.seek(new TopicPartition("testkafka", 1), 2555)

  }
}
