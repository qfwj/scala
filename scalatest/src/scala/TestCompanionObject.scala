object TestCompanionObject{
  var test = 0
  def out(x :Int) = {
    val ss = new TestCompanionObject
    ss.sum(3)
    print(ss.sum)
  }
}

class TestCompanionObject {
  var sum = 0
  def sum(x:Int) = x
}