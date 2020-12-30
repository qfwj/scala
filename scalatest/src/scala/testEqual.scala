/**
  * @ClassName: testEqual
  * @Description:
  * @author: zhbo
  * @date: 2018/12/3 下午4:47
  * @Copyright: 2017 . All rights reserved.
  *
  */
object testEqual {
  def main(args: Array[String]): Unit = {

  }
}

class Point1(val x:Int, val y:Int) {

  override  def equals(x :Any) = x match {
    case that:Point1 => that.x == this.x && this.y == that.y
    case _ => false
  }

}



