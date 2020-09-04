package domain

import cats.data.Validated.{Invalid, Valid}
import domain.helpers.DomainError

package object validation {
  implicit class DomainValidationOps[T](self: ValidationResult[T]) {
    def ++(that: ValidationResult[T]): ValidationResult[T] =
      (self, that) match {
        case (Valid(v1), Valid(_))      => Valid(v1)
        case (Valid(_), Invalid(v2))    => Invalid(v2)
        case (Invalid(v1), Valid(_))    => Invalid(v1)
        case (Invalid(v1), Invalid(v2)) => Invalid(v1 ::: v2)
      }

    def foldToEither(): Either[DomainError, T] =
      self.fold(
        e => Left(e.reduceLeft(_ ++ _)),
        value => Right(value)
      )
  }
}
