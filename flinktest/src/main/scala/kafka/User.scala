package kafka

/**
  * @Description: TODO
  * @author: zhbo
  * @date 2019/8/27 16:52
  */
case class User(val name:String, val age:Int) {
  override  def toString() =   name + age

}
