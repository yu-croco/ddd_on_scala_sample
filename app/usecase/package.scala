package usecase

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

package object usecase {
  implicit class FutureOptionOps[T](maybeFutureValue: Future[Option[T]])(implicit ex: ExecutionContext) {
    def toUseCaseError(key: String, message: String): Future[T] =
      maybeFutureValue.transformWith {
        case Success(Some(value)) => Future.successful(value)
        case Success(None)        => Future.failed(UseCaseError(key, message))
        case Failure(exception)   => Future.failed(exception)
      }
  }
}
