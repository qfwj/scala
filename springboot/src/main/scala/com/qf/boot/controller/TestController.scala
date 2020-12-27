package com.qf.boot.controller

import java.util
import java.util.List

import javax.annotation.PostConstruct
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.Acknowledgment
import org.springframework.web.bind.annotation.{GetMapping, RestController}
import scala.collection.JavaConverters._

@RestController
class TestController {

  @Autowired
  var kafkaTemplate: KafkaTemplate[String, String] = null

  @PostConstruct
  @GetMapping(Array("/testkafka")) def main1(): Unit = {
    kafkaTemplate.send("testkafka1", "tessadasd")
  }


  //@KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = "${channel.topic}", containerFactory = "batchFactory")
  @KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = Array("testkafka1"), containerFactory = "batchFactory")
  def listen(records: util.List[ConsumerRecord[String, String]], ack: Acknowledgment): Unit = {
    val map =  records.asScala.groupBy(f=>f.partition).toMap
    ack.acknowledge()
  }
}
