package com.qf.boot.controller

import java.util
import java.util.List

import javax.annotation.PostConstruct
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.{Acknowledgment, SendResult}
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.web.bind.annotation.{GetMapping, RestController}

import scala.collection.JavaConverters._

@RestController
class TestController {

  @Autowired
  var kafkaTemplate: KafkaTemplate[String, String] = null

 // @PostConstruct
  @GetMapping(Array("/testkafka")) def main1() = {
    var i = 0

    while(i < 10){
      i +=1
      val senR: ListenableFuture[SendResult[String,String]] = kafkaTemplate.send("testkafka", "tessadasd" + i)
      senR.addCallback( (v:SendResult[String,String])=>{
        println(v)
      },(f:Throwable)=>{
        println(f)
      })
    }
    "end"
  }


  //@KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = "${channel.topic}", containerFactory = "batchFactory")
//  @KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = Array("testkafka"), containerFactory = "batchFactory")
  def listen(records: util.List[ConsumerRecord[String, String]], ack: Acknowledgment): Unit = {
    val map =  records.asScala.groupBy(f=>f.partition).map(f=>(f._1,f._2.maxBy(f1=>f1.offset())))
    records.asScala
    println("offset 记录"+ map)
    ack.acknowledge()
  }
}
