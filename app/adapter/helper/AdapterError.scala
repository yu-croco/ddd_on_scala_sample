package adapter.helper

import cats.data.NonEmptyList

case class AdapterError(detail: NonEmptyList[String]) extends AnyVal

object AdapterError {
  def create(detail: NonEmptyList[String]): AdapterError = apply(detail)
}
