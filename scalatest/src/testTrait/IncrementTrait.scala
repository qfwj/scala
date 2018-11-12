package testTrait


trait IncrementTrait extends IntQueue{
  override def put(ss: Int): Unit = {
    super.put(ss + 1)
  }
}
