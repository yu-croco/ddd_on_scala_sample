import cats.data.{Validated, ValidatedNel}
import domain.helper.DomainError

package object domain {
  trait BaseFactory[R, V] {
    def className = this.getClass.getSimpleName
    def apply(value: R): V

    def isValid(value: R): Boolean
    def errorMessage(value: R): String

    def create(value: R): Validated[DomainError, V] =
      Validated.cond(isValid(value), apply(value), DomainError.create(className, errorMessage(value)))

    def createNel(value: R): ValidatedNel[DomainError, V] = create(value).toValidatedNel
  }

  trait NonEmptyStringVOFactory[T] extends BaseFactory[String, T] {
    def isValid(value: String): Boolean       = !value.isEmpty
    def errorMessage(message: String): String = s"$message should not be blank"
  }

  trait NonNegativeLongVOFactory[T] extends BaseFactory[Long, T] {
    override def isValid(value: Long): Boolean     = value >= 0
    override def errorMessage(value: Long): String = s"$value should not be negative"
  }

  trait EntityIdFactory[T] extends NonEmptyStringVOFactory[T] {
    val UUID         = java.util.UUID.randomUUID.toString
    def initialize() = apply(UUID)
  }

}
