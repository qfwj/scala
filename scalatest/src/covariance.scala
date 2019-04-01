import CovariantAndContravariantDemo._


class sup


class sonson

class test[+T] {
  def test[u >: T](x: u): Unit = {
    println(x)
  }
}


/*class c1[+T] {

  def method[K >: T](x: K) {println(x)}
}
class c2[T] extends c1[T]*/

trait c1[T] {

  def method[K >: T](x: K) = x
}

object c2 extends c1[Int]


object CovariantAndContravariantDemo {
  def main(args: Array[String]): Unit = {

    c2.method(3) // 3

    c2.method(3.0) // 3.0

    c2.method("abc") // "abc"

    val son1: son = new son
    val sup1: sup = new sup
    val sonson1: sonson = new sonson

    val test11: test[son] = new test[son]
    test11.test(sonson1)
  }

}