abstract class Element {
  def contents:Array[String]
  def height = contents.length
  def width = if (height ==0) 0 else contents(0).length

}
