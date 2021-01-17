package com.qf.guava

import com.google.common.collect.{Collections2, ImmutableSet, Lists, Sets}

object TestCollections extends  App {

  val set = Sets.newHashSet(2,2,3,4)
  Sets.newTreeSet()

  val list = Lists.newArrayList(1,2,3,4)
  val list1 =  Lists.reverse(list)
  println( Lists.partition(list,2))
  println(list)
  println(list1)

  val immutableSet = ImmutableSet.of(12,23)
  Collections2.filter(immutableSet, (f:Int) => f > 20)
  Collections2.permutations(immutableSet)
}
