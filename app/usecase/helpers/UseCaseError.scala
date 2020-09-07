package usecase.helpers

import cats.data.NonEmptyList

case class UseCaseError(error: NonEmptyList[String]) extends Throwable

object UseCaseError {
  def create(message: String): UseCaseError             = apply(NonEmptyList.one(message))
  def create(error: NonEmptyList[String]): UseCaseError = apply(error)
}
