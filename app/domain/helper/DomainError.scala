package domain.helper

import cats.data.NonEmptyList

case class DomainError(detail: NonEmptyList[(String, String)]) extends Throwable

object DomainError {
  def create(key: String, message: String) = DomainError(NonEmptyList.one(key -> message))
}
