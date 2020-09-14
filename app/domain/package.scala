import cats.data.{Validated, ValidatedNel}
import domain.helpers.DomainError

package object domain {
  type ValidationResult[T] = ValidatedNel[DomainError, T]

  trait DomainIDFactory[T] {
    val UUID = java.util.UUID.randomUUID.toString
    val reg  = "\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}".r

    def error: DomainError
    def apply(t: String): T
    def test(t: String): Boolean               = reg.findFirstMatchIn(t).isDefined
    def create(t: String): ValidationResult[T] = Validated.condNel(test(t), apply(t), error)
  }
}
