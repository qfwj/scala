package testTrait


trait DoubleTrait extends IntQueue{
  abstract override def put(ss: Int): Unit = {
    super.put(ss * 2)
  }

}
