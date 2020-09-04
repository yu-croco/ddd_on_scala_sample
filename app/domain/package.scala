import cats.data.{Validated, ValidatedNel}
import domain.helper.DomainError

package object domain {
  type ValidationResult[U] = ValidatedNel[DomainError, U]

  trait Specification[T, U] {
    def className = this.getClass.getSimpleName
    def test(t: T): Boolean
    def error: DomainError
    def apply(t: T): U
    def create(t: T): ValidationResult[U] = Validated.condNel(test(t), apply(t), error)
  }

  trait NonEmptyStringVOFactory[T] extends Specification[String, T] {
    def test(t: String): Boolean = !t.isEmpty
    def error: DomainError       = DomainError.create(className, "空欄です")
  }

  trait NonNegativeLongVOFactory[T] extends Specification[Long, T] {
    def test(t: Long): Boolean = t >= 0
    def error: DomainError     = DomainError.create(className, "マイナス値です")
  }

  trait EntityIdFactory[U] extends Specification[String, U] {
    override def error: DomainError       = DomainError.create(className, "IDの形式に誤りがあります")
    val UUID                              = java.util.UUID.randomUUID.toString
    private val reg                       = "\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}".r
    override def test(t: String): Boolean = reg.findFirstMatchIn(t).isDefined
  }
}
