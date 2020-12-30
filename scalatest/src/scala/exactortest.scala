
object exactortest {
  def main(args: Array[String]): Unit = {
    //val out = Email.unapply(Email.apply("uu","dd"))
    test("sdsf@cdcd.com")


    val currency = Currency(30.2, "EUR")

    currency match {
      case Currency(amount, "USD") => println("$" + amount)
      case _ => println("No match.")
    }


  }
def test(ss : Any) = ss match {
  case Email(ss) => {
    println(ss)
  }
  case _ =>None
}
}

/*case class Email(user:String){

}*/

class Currency(val value: Double, val unit: String) {

}

object Currency{

  def apply(value: Double, unit: String): Currency = new Currency(value, unit)

  def unapply(currency: Currency): Option[(Double, String)] = {
    if (currency == null){
      None
    }
    else{
      Some(currency.value, currency.unit)
    }
  }
}





 case class  Email(email:String){


}

object Email{
  def apply(user: String, domain: String): Email = new Email(user +"@"+domain)
  def unapply(arg: Email): Option[(String, String)] = {
    val arr = arg.email.split("@")
    if(arr.length > 1) Some(arr(0),arr(1)) else None
  }
}