package com.qf.boot.service

import java.time.Duration
import java.util.Collections

import javax.annotation.PostConstruct
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * description:  <br>
 * date: 2020/12/31 10:25 <br>
 * author: zhbo <br>
 */
@Service
class TestConsumer {

  @Autowired
  val consumer: KafkaConsumer[String, String] = null

  @PostConstruct
  def post(): Unit = {
    consumer.subscribe(Collections.singleton("testkafka"))
    //val toic  = new TopicPartition()
    while (true) {
      consumer.poll(Duration.ofSeconds(20)).forEach(f => {
        val topic = f.topic
        val partiton = f.partition
        val offset = f.offset
        val value = f.value
        println(s"topic: $topic   partition:$partiton  offset:$offset  value: $value"  )
      })
      consumer.pause()

      consumer.seek(new TopicPartition("testkafka", 1), 714)
      consumer.resume()
    }
  }
}
