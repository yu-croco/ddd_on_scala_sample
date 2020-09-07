package usecase

import domain.helpers.DomainError

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import helpers.UseCaseError
import org.atnos.eff.future.{_future, fromFuture}
import org.atnos.eff.{|=, either, Eff}

package object usecase {
  type UseCaseEither[T]  = Either[UseCaseError, T]
  type _useCaseEither[R] = UseCaseEither |= R

  implicit class FutureOptionOps[T](maybeFutureValue: Future[Option[T]])(implicit ex: ExecutionContext) {
    def toUCErrorIfNotExists(message: String): Future[T] =
      maybeFutureValue.transformWith {
        case Success(Some(value)) => Future.successful(value)
        case Success(None)        => Future.failed(UseCaseError.create(s"${message}は見つかりませんでした"))
        case Failure(exception)   => Future.failed(exception)
      }
  }

  implicit class FutureOps[T](futureValue: Future[T])(implicit ex: ExecutionContext) {
    def raiseIfFutureFailed(message: String): Future[T] =
      futureValue.transformWith {
        case Success(value) => Future.successful(value)
        case Failure(exception) =>
          Future.failed(UseCaseError.create(s"${message}のデータ更新に失敗しました。詳細: ${exception.getMessage}"))
      }

    def toEff[R: _future]: Eff[R, T] = fromFuture(futureValue)
  }

  implicit class EitherDomainErrorOps[T](eitherValue: Either[DomainError, T]) {
    def toUCErrorIfLeft(): Either[UseCaseError, T] =
      eitherValue.left.map(err => UseCaseError.create(err.detail))
  }

  implicit class EitherUCErrorOps[T](eitherValue: Either[UseCaseError, T]) {
    def toEff[R: _useCaseEither]: Eff[R, T] = either.fromEither(eitherValue)
  }
}
