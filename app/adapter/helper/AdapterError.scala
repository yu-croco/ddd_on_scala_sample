package adapter.helper

import cats.data.NonEmptyList

case class AdapterError(detail: NonEmptyList[(String, String)])
