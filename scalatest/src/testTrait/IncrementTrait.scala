package testTrait


trait IncrementTrait extends IntQueue{
  abstract override def put(ss: Int): Unit = {
    super.put(ss + 1)
  }
}
