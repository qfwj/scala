package com.qf.guava

import java.time.Duration

import com.google.common.cache.{Cache, CacheBuilder, CacheLoader}

object TestGuava extends  App {

val cache: Cache[Any, Any] = CacheBuilder.newBuilder()
  .maximumSize(3)
  .expireAfterWrite(Duration.ofSeconds(10))
  .build()
  .asInstanceOf[Cache[Any,Any]]
  cache.put(12,13)
  cache.put(11,13)
  cache.put(10,13)
  println(cache.asMap())
  Thread.sleep(5000)
  cache.put(9,13)
  println(cache.asMap())
  Thread.sleep(6000)

  //cache.put(10,13)

  println(cache.asMap())

}
