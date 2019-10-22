package kafka

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/8/27 16:52
  */
case class User(val name:String, val age:Int, val createTime:Long) {
  override def toString() = name + "|| Age:" + age + "|| Time:" + System.currentTimeMillis()

  override def equals(obj: scala.Any): Boolean = obj match {
    case dd: User => dd.name.eq(this.name)
    case _ => false
 }

}
