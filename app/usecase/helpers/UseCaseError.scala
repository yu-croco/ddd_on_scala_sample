package usecase.helpers

import cats.data.NonEmptyList

case class UseCaseError(error: NonEmptyList[(String, String)]) extends Throwable

object UseCaseError {
  def apply(key: String, message: String): UseCaseError = UseCaseError(NonEmptyList.one(key -> message))
}
