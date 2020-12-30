package com.qf.partialfunction

/**
 * description:  <br>
 * date: 2020/12/30 14:56 <br>
 * author: zhbo <br>
 */
class TestPartial extends PartialFunction[Int, String] {
  override def isDefinedAt(x: Int): Boolean = {
    true
  }

  override def apply(v1: Int): String = {
    ""
  }
}
