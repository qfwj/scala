package com.qf.boot.config

import java.util
import java.util.{HashMap, Map}

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.{Bean, Configuration, DependsOn}
import org.springframework.kafka.config.{ConcurrentKafkaListenerContainerFactory, KafkaListenerContainerFactory}
import org.springframework.kafka.core.{ConsumerFactory, DefaultKafkaConsumerFactory}
import org.springframework.kafka.listener.ContainerProperties

@Configuration
class KafkaConfig {
  @Bean def consumerFactory(propsConfig: KafkaProperties) = new DefaultKafkaConsumerFactory[Integer, String](consumerConfigs(propsConfig))

  @Autowired private[config] val kafkaProperties:KafkaProperties = null

  @Bean def batchFactory(consumerFactory: ConsumerFactory[Integer, String]): KafkaListenerContainerFactory[_] = {
    val factory = new ConcurrentKafkaListenerContainerFactory[Integer, String]
    factory.setConsumerFactory(consumerFactory)
    factory.getContainerProperties.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE)
    //factory.setConcurrency(kafkaProperties.getConcurrent());
    factory.setBatchListener(true)
    factory
  }


  //    @Bean
  //    @ConfigurationProperties(prefix = "spring.kafka.consumer")
  //    public KafkaProperties getKafkaProperties(){
  //        KafkaProperties kafkaProperties = new KafkaProperties();
  //        return kafkaProperties;
  //    }


  @Bean
  def consumerConfigs(propsConfig: KafkaProperties): util.HashMap[String, AnyRef] = {
    val propsMap = new util.HashMap[String, AnyRef]
    propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, propsConfig.getBootstrapServers)
    // propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, propsConfig.getEnableAutoCommit());
    propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100")
    propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000")
    propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    propsMap.put(ConsumerConfig.GROUP_ID_CONFIG,"scalatest6")
    propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    // propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, propsConfig.getProperties().gget);
    propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    // propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, propsConfig.getMaxPollRecords());
    propsMap
  }

  @Bean
  //@DependsOn(Array("consumerConfigs"))
  def consumer(@Qualifier("consumerConfigs") consumerConfigs: util.HashMap[String, AnyRef]) = {
    val consumer:KafkaConsumer[String, String] = new KafkaConsumer(consumerConfigs)
    consumer
  }
}
