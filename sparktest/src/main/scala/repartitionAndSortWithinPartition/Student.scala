package repartitionAndSortWithinPartition

class Student(val name: String, val age: Int, val address: String) extends   Ordered[Student] with Serializable {


  override def toString: String = "name:" + name + ",age:"+ age + ",address:" + address


  override def compareTo(that: Student): Int = super.compareTo(that)

  override def compare(that: Student): Int = {
    this.age - that.age
  }
}
