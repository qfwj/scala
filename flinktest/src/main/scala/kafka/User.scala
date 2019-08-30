package kafka

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/8/27 16:52
  */
case class User(val name:String, val age:Int, val createTime:Long) {
  override  def toString() =   name + "|| Age:" + age + "|| Time:" + createTime

}
