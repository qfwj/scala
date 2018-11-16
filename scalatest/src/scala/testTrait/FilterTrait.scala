package testTrait


trait FilterTrait extends IntQueue{
  abstract override def get(): Int = {
    val temp = super.get()
    if (temp < 0 )
      temp else
      0
  }
}
