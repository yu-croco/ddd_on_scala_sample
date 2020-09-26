import cats.data.{Validated, ValidatedNel}
import domain.helpers.DomainError

package object domain {
  type ValidationResult[T] = ValidatedNel[DomainError, T]

  trait BaseFactory[S, T] {
    def error: DomainError
    def apply(value: S): T
    def test(value: S): Boolean
    def create(value: S): ValidationResult[T] = Validated.condNel(test(value), apply(value), error)
  }

  // 簡素なUUIDを使っているケースを想定
  trait DomainIDFactory[T] extends BaseFactory[String, T] {
    val UUID = java.util.UUID.randomUUID.toString
    val reg  = "\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}".r

    override def test(value: String): Boolean = reg.findFirstMatchIn(value).isDefined
  }
}
