package com.qf.guava

import java.time.Duration
import java.util

import com.google.common.cache.{Cache, CacheBuilder, CacheLoader}

import scala.collection.JavaConverters._

object TestGuava extends App {

  val cache1 = CacheBuilder.newBuilder.expireAfterAccess(Duration.ofMinutes(30))
    .build(new CacheLoader[String, util.Set[String]]() {
      @throws[Exception]
      override def load(key: String): util.Set[String] = {
        // outerSourceDao.getDenyWord(key, "", 1, Integer.MAX_VALUE).stream.map((f: Any) => {
        Set("", "", "dsd").asJava

      }
    })
  cache1.get("adsad")
  println(cache1)
  val cache: Cache[Any, Any] = CacheBuilder.newBuilder()
    .maximumSize(3)
    .expireAfterWrite(Duration.ofSeconds(10))
    .build(CacheLoader.from(() => {
      ""
    })).asInstanceOf[Cache[Any, Any]]

  cache.put(12, 13)
  cache.put(11, 13)
  cache.put(10, 13)
  println(cache.asMap())
  Thread.sleep(5000)
  cache.put(9, 13)
  println(cache.asMap())
  Thread.sleep(6000)

  //cache.put(10,13)

  println(cache.asMap())

}
