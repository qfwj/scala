package testTrait


trait DoubleTrait extends IntQueue{
  override def put(ss: Int): Unit = {
    super.put(ss * 2)
  }

}
