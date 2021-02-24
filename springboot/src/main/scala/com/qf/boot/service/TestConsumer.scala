package com.qf.boot.service

import java.time.Duration
import java.util.Collections
import java.util.concurrent.atomic.AtomicBoolean

import javax.annotation.PostConstruct
import org.apache.kafka.clients.consumer.{ConsumerRebalanceListener, KafkaConsumer}
import org.apache.kafka.clients.consumer.internals.ConsumerCoordinator
import org.apache.kafka.common.TopicPartition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.{GetMapping, RestController}

import scala.collection.JavaConverters._
/**
 * description:  <br>
 * date: 2020/12/31 10:25 <br>
 * author: zhbo <br>
 */
//@Service
@RestController
class TestConsumer {

  @Autowired
  val consumer: KafkaConsumer[String, String] = null
  @volatile  var pauseOpen = new AtomicBoolean(false)
  @volatile   var resumeOpen = new AtomicBoolean(false)


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
      consumer.seek(new TopicPartition("testkafka", 1), 714)
    }
    new Thread(()=> {
      consumer.subscribe(Collections.singleton("testkafka"), new ConsumerRebalanceListenerT(consumer))
      while (true) {
        consumer.poll(Duration.ofSeconds(3)).forEach(f => {
          val topic = f.topic
          val partiton = f.partition
          val offset = f.offset
          val value = f.value
          println(s"topic: $topic   partition:$partiton  offset:$offset  value: $value"  )
        })
        println("poll")

        if(pauseOpen.get()&& pauseOpen.compareAndSet(true, false)){
          pauseConsumer
        }
        if(  resumeOpen.get() && resumeOpen.compareAndSet(true,false)){
          resumeConsumer
        }
      }
    }).start()

  }

  def pauseConsumer: Unit ={
    consumer.pause((List(new TopicPartition("testkafka", 1),
      new TopicPartition("testkafka", 2),
      new TopicPartition("testkafka", 0),
    ).asJavaCollection))

  }

  def wakeUpConsumer: Unit ={
    /**
     * https://blog.csdn.net/CHS007chs/article/details/102899288
     * 进行hook添加确保优雅关闭
     *   consumer.wakeup 唯一可以使用外部线程执行的方法
     * try // 主线程继续执行，以便可以关闭consumer，提交偏移量
     *mainThread.join
     * catch {
     * case e: InterruptedException =>
     *e.printStackTrace()
     * }
     *
     */
    consumer.wakeup

  }

  def resumeConsumer: Unit ={
    consumer.resume(
      List(
      new TopicPartition("testkafka", 1),
      new TopicPartition("testkafka", 2),
      new TopicPartition("testkafka", 0),
    ).asJavaCollection)
    consumer.seek(new TopicPartition("testkafka", 1), 2555)
    consumer.seek(new TopicPartition("testkafka", 0), 2935)
    consumer.seek(new TopicPartition("testkafka", 2), 2300)
  }

  @GetMapping(Array("/pause"))
  def pause()={
    pauseOpen.compareAndSet(false, true)
  }
  @GetMapping(Array("/resume"))
  def resume()={

    resumeOpen.compareAndSet(false,true)
  }
}
