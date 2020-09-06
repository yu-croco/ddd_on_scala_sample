package usecase.helpers

import cats.data.NonEmptyList

case class UseCaseError(error: NonEmptyList[(String, String)]) extends Throwable

object UseCaseError {
  def create(key: String, message: String): UseCaseError          = apply(NonEmptyList.one(key -> message))
  def create(error: NonEmptyList[(String, String)]): UseCaseError = apply(error)
}
