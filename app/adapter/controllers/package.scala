package adapter

import adapter.controllers.helpers.JsonHelper
import play.api.libs.json.{Json, Writes}
import play.api.mvc.Result
import play.api.mvc.Results.{Created, Ok}
import usecase.helper.UseCaseError

import scala.concurrent.{ExecutionContext, Future}

package object controllers extends JsonHelper {
  implicit class FutureOps[T](value: Future[T])(implicit ec: ExecutionContext) {
    def toSuccessResponse(implicit writes: Writes[T]): Future[Result] =
      value
        .map(v => successJson(Ok, Json.toJson(v)))
        .returnErrorIfExists()

    def toCreateResponse(implicit writes: Writes[T]): Future[Result] =
      value
        .map(v => successJson(Created, Json.toJson(v)))
        .returnErrorIfExists()
  }

  implicit class FutureOptionOps[T](value: Future[Option[T]])(implicit ec: ExecutionContext) {
    def toSuccessResponse(implicit writes: Writes[Option[T]]): Future[Result] =
      value
        .map(v => successJson(Ok, Json.toJson(v)))
        .returnErrorIfExists()

    def toCreateResponse(implicit writes: Writes[Option[T]]): Future[Result] =
      value
        .map(v => successJson(Created, Json.toJson(v)))
        .returnErrorIfExists()
  }

  implicit class FutureSeqOps[T](value: Future[Seq[T]])(implicit ec: ExecutionContext) {
    def toSuccessResponse(implicit writes: Writes[Seq[T]]): Future[Result] =
      value
        .map(v => successJson(Ok, Json.toJson(v)))
        .returnErrorIfExists()

    def toCreateResponse(implicit writes: Writes[Seq[T]]): Future[Result] =
      value
        .map(v => successJson(Created, Json.toJson(v)))
        .returnErrorIfExists()
  }

  implicit class FutureResultOps[T <: Result](futureResult: Future[T])(implicit ec: ExecutionContext) {
    def returnErrorIfExists(): Future[Result] = futureResult.recover {
      case e: UseCaseError => toFailedProcessError(e)
    }
  }
}
