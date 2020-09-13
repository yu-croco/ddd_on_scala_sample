package domain.helpers.ops

import cats.data.Validated
import domain.ValidationResult
import domain.helpers.DomainError

object DomainValidationOps {
  implicit class DomainValidationOps[T](self: ValidationResult[T]) {
    def foldToEither(): Either[DomainError, T] =
      self.fold(
        e => Left(e.reduceLeft(_ ++ _)),
        value => Right(value)
      )
  }

  def execWithValidation[T](test: Boolean, rightResult: T, leftResult: DomainError): Either[DomainError, T] =
    validate[T](test, rightResult, leftResult).foldToEither()

  private def validate[T](test: Boolean, rightResult: T, leftResult: DomainError): ValidationResult[T] =
    Validated
      .cond(
        test,
        rightResult,
        leftResult
      )
      .toValidatedNel
}
