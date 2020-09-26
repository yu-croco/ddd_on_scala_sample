package domain.helpers

import cats.data.NonEmptyList

case class DomainError(detail: NonEmptyList[String]) extends Throwable {
  def ++(other: DomainError): DomainError = this.copy(this.detail ::: other.detail)
}

object DomainError {
  def create(message: String): DomainError = DomainError.apply(NonEmptyList.one(message))
}
