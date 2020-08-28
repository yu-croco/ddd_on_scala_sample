package domain.helper

import cats.data.NonEmptyList

trait DomainError extends Throwable {
  val detail: NonEmptyList[(String, String)]
}

case class DomainValidationError(override val detail: NonEmptyList[(String, String)]) extends DomainError

object DomainValidationError {
  def create(key: String, message: String) = DomainValidationError(NonEmptyList.one(key -> message))
}
