import cats.data.{Validated, ValidatedNel}
import domain.helper.DomainError

package object domain {
  trait BaseDomainSpecificationFactory[R, V] {
    def className = this.getClass.getSimpleName
    def apply(value: R): V

    def isSatisfied(value: R): Boolean
    def errorMessage(value: R): String

    def create(value: R): Validated[DomainError, V] =
      Validated.cond(isSatisfied(value), apply(value), DomainError.create(className, errorMessage(value)))

    def createNel(value: R): ValidatedNel[DomainError, V] = create(value).toValidatedNel
  }

  trait NonEmptyStringVODomainSpecificationFactory[T] extends BaseDomainSpecificationFactory[String, T] {
    def isSatisfied(value: String): Boolean   = !value.isEmpty
    def errorMessage(message: String): String = s"$message should not be blank"
  }

  trait NonNegativeLongVODomainSpecificationFactory[T] extends BaseDomainSpecificationFactory[Long, T] {
    override def isSatisfied(value: Long): Boolean = value >= 0
    override def errorMessage(value: Long): String = s"$value should not be negative"
  }

  trait EntityIdDomainSpecificationFactory[T] extends NonEmptyStringVODomainSpecificationFactory[T] {
    val UUID         = java.util.UUID.randomUUID.toString
    def initialize() = apply(UUID)
  }

}
