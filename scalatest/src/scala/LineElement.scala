
class LineElement(s:String) extends ArrayElement(s) {
  override def height: Int = 1
  override def width: Int = s.length
}
