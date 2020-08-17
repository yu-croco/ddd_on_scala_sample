import domain.helper.{DomainError, DomainValidationError}

package object domain {
  trait BaseFactory[M, K] {
    def isValid(value: M): Boolean
    def apply(value: M): K
    def errorMessage(value: M): String
    def className = this.getClass.getName
    def create(value: M): Either[DomainError, K] =
      Either.cond(isValid(value), apply(value), DomainValidationError.create(className, errorMessage(value)))
  }

  object StringFactory {
    trait NonEmptyStringFactory[T] extends BaseFactory[String, T] {
      def isValid(value: String): Boolean       = !value.isEmpty
      def errorMessage(message: String): String = s"$message should not be blank"
    }
  }

  object LongFactory {
    trait NonNegativeLongFactory[T] extends BaseFactory[Long, T] {
      override def isValid(value: Long): Boolean = value >= 0

      override def errorMessage(value: Long): String = s"$value should not be negative"
    }
  }
}
