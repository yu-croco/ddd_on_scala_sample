package usecase

import cats.data.NonEmptyList

case class UseCaseError(error: NonEmptyList[(String, String)]) extends Throwable {
  def ++(other: UseCaseError): UseCaseError = this.copy(error = this.error ::: other.error)
}

object UseCaseError {
  def apply(key: String, message: String) = UseCaseError(NonEmptyList.one(key -> message))
}
