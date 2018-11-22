package covariance


class QueueT[+T] private(private[this] var leading: List[T], private[this] var trailing: List[T]) {

  def mirror() =
    if (leading.isEmpty) {
      while (!trailing.isEmpty) {
        leading = trailing.head :: leading
        trailing = trailing.tail
      }
    }
  def head = {
    mirror()
    leading.head
  }


  def tail = {
    mirror()
    new QueueT(leading.tail, trailing)
  }

  def append[U>:T](x:U) = {
    new QueueT[U](leading, x :: trailing)
  }
}
