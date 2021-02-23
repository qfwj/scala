package com.qf.caffaine

import java.util.concurrent.TimeUnit

import com.github.benmanes.caffeine.cache.{CacheLoader, Caffeine}

/**
 * description:  
 * date: 2021/2/22 10:02 
 * author: zhbo 
 */
object CafaineTest  extends  App {

  val cafaineTest = Caffeine.newBuilder()
    // 设置最后一次写入或访问后经过固定时间过期
    .expireAfterWrite(60, TimeUnit.SECONDS)
    // 初始的缓存空间大小
    .initialCapacity(100)
    // 缓存的最大条数
    .maximumSize(1000).build(
    (key: String) => {
      "dsad"
    }
  )
  cafaineTest.get("")

}
