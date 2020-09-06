import cats.data.ValidatedNel
import domain.helpers.DomainError

package object domain {
  type ValidationResult[U] = ValidatedNel[DomainError, U]
}
