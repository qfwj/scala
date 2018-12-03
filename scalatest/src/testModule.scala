
object testModule {

}


trait SimpleFoods {

  object Pear extends Food

  def allFoods = List(Pear)

  def allCategories = Nil
}

trait SimpleRecipes {
  this: SimpleFoods =>

  object FruitSalad extends Recipe("fruit salad", List(Pear))

  def allRecipes = List(FruitSalad)
}


abstract class Recipe(name: String, food: List[Food]) {

}

abstract class Food(val name: String) {
  override def toString = name
}