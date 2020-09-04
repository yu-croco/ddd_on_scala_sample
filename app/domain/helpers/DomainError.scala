package domain.helpers

import cats.data.NonEmptyList

case class DomainError(detail: NonEmptyList[(String, String)]) extends Throwable {
  def ++(other: DomainError): DomainError = this.copy(this.detail ::: other.detail)
}

object DomainError {
  def create(key: String, message: String) = DomainError(NonEmptyList.one(key -> message))
}
