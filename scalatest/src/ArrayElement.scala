/*

class ArrayElement(conte:Array[String], private val age:Int) extends Element{
  def contents = conte
}
*/

/*parametric fields */
class ArrayElement( val contents:Array[String]) extends Element {
  def this(s:String) = this(Array(s))
  final def  finanltest() = "tert"
}




object ArrayElement {
  def apply( test:Array[String]): ArrayElement = new ArrayElement(test) //不用new 就可以进行创建了
}
