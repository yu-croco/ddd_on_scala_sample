import cats.data.ValidatedNel
import domain.helper.{DomainError, DomainValidationError}

package object domain {
  type ValidationResult[A] = ValidatedNel[DomainError, A]

  trait BaseFactory[R, V] {
    def className = this.getClass.getName
    def apply(value: R): V

    def isValid(value: R): Boolean
    def errorMessage(value: R): String

    def create(value: R): Either[DomainError, V] =
      Either.cond(isValid(value), apply(value), DomainValidationError.create(className, errorMessage(value)))
  }

  trait NonEmptyStringVOFactory[T] extends BaseFactory[String, T] {
    def isValid(value: String): Boolean       = !value.isEmpty
    def errorMessage(message: String): String = s"$message should not be blank"
  }

  trait NonNegativeLongVOFactory[T] extends BaseFactory[Long, T] {
    override def isValid(value: Long): Boolean     = value >= 0
    override def errorMessage(value: Long): String = s"$value should not be negative"
  }

}
