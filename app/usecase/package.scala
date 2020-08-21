package usecase

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import helper.UseCaseError
import org.atnos.eff.future.{_future, fromFuture}
import org.atnos.eff.{|=, Eff}

package object usecase {
  type UseCaseEither[T]  = UseCaseError Either T
  type _useCaseEither[R] = UseCaseEither |= R

  implicit class FutureOptionOps[T](maybeFutureValue: Future[Option[T]])(implicit ex: ExecutionContext) {
    def ifNoeExists(key: String, message: String): Future[T] =
      maybeFutureValue.transformWith {
        case Success(Some(value)) => Future.successful(value)
        case Success(None)        => Future.failed(UseCaseError(key, message))
        case Failure(exception)   => Future.failed(exception)
      }
  }

  implicit class FutureOps[T](futureValue: Future[T])(implicit ex: ExecutionContext) {
    def rollbackAndRaiseIfFutureFailed(key: String): Future[T] =
      futureValue.transformWith {
        case Success(value)     => Future.successful(value)
        case Failure(exception) => Future.failed(UseCaseError(key, s"raise error. detail: ${exception.getMessage}"))
      }

    def toEff[R: _future]: Eff[R, T] = fromFuture(futureValue)
  }
}
