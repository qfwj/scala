
class Rational(n:Int, d: Int) {



  println("tees3434")
  private  var g = gcd(d.abs,n.abs)
  val nn = n / g
  val dd = d / g

  private def max(n:Int, d:Int) = d

  def +(r1 :Rational) = new Rational(this.dd  * r1.nn + this.nn  * r1.dd, this.dd * r1.dd)
  def *(r1 :Rational) = new Rational( r1.nn  * r1.nn, this.dd * r1.dd)

  def -(r1 :Rational) = new Rational(this.nn  * r1.dd -  this.dd  * r1.nn, this.dd * r1.dd)
  def /(r1 :Rational) = new Rational(this.nn  * r1.dd, this.dd  * r1.nn)
  def *(d: Int) = new Rational(this.nn * d, this.dd)
  private def gcd(a:Int,b :Int): Int = {
    if(b==0) a  else gcd(b ,a % b)
  }

  override def toString: String = this.nn + "/" +this.dd


}
