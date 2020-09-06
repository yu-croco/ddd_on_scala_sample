package adapter.helper

import cats.data.NonEmptyList

case class AdapterError(detail: NonEmptyList[(String, String)]) extends AnyVal

object AdapterError {
  def create(detail: NonEmptyList[(String, String)]): AdapterError = apply(detail)
}
