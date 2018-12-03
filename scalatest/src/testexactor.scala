
object testexactor {

  def main(args: Array[String]): Unit = {
    test("sdsd@dasdas.com")
    val testExactor = "sdsd@dasdas.com"
    testExactor match {
      case Exactor(name, mana) => {
        val sdsd = Exactor(testExactor)
        println(sdsd)
      }
    }

  }

  def test(test: Any) = test match {
    case Exactor(user, domain) => {
      println(user, domain)
    }
    case _ => None
  }

}


class Exactor(user: String, domain: String) {

}


object Exactor {
  def apply(user: String): String = {
    "dasdasd"
  }

 def unapply(arg: String): Option[(String, String)] = {
    Option(arg.split("@")(0), arg.split("@")(1))
  }

  def unapplySeq(whole: String): Option[Seq[String]] =
    Some(whole.split("\\.").reverse)
}





object Domain {
  // The injection method (optional)
  def apply(parts: String*): String =
    parts.reverse.mkString(".")
  // The extraction method (mandatory)
  def unapplySeq(whole: String): Option[Seq[String]] =
    Some(whole.split("\\.").reverse)
}
/*
object EMail extends （(String, String) => String） {  }*/
