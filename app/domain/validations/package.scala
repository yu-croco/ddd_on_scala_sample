package domain

import cats.data.Validated
import domain.helpers.DomainError

package object validation {
  implicit class DomainValidationOps[T](self: ValidationResult[T]) {
    def foldToEither(): Either[DomainError, T] =
      self.fold(
        e => Left(e.reduceLeft(_ ++ _)),
        value => Right(value)
      )
  }

  def singleValidate[T](test: Boolean, rightResult: T, leftResult: DomainError): Either[DomainError, T] =
    validate[T](test, rightResult, leftResult).foldToEither()

  private def validate[T](test: Boolean, rightResult: T, leftResult: DomainError): ValidationResult[T] =
    Validated.cond(test, rightResult, leftResult).toValidatedNel
}
