object StateMonad {

  import State._

  trait State[S, +A] {
    def apply(s: S): (S, A)

    def map[B](f: A => B): State[S, B] = state(apply(_) match {
      case (s, a) => (s, f(a))
    })

    def flatMap[B](f: A => State[S, B]): State[S, B] =
      state(apply(_) match {
        case (s, a) => f(a)(s)
      })
  }

  object State {
    def state[S, A](f: S => (S, A)) = new State[S, A] {
      def apply(s: S) = f(s)
    }

    def init[S]: State[S, S] = state[S, S](s => (s, s))

    def modify[S](f: S => S) =
      init[S] flatMap (s => state(_ => (f(s), ())))
  }

}