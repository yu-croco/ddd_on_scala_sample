package adapter

import adapter.controllers.JsonOps.toFailedProcessError
import adapter.controllers.helpers.{CirceJsonHelper, JsonHelper}
import play.api.http.Writeable
import play.api.libs.json.{Json, Writes}
import play.api.mvc.Result
import play.api.mvc.Results.{Created, Ok}
import usecase.helper.UseCaseError

import scala.concurrent.{ExecutionContext, Future}

package object controllers {
  implicit class FutureResultOps[T <: Result](futureResult: Future[T])(implicit ec: ExecutionContext) {
    def returnErrorIfExists(): Future[Result] = futureResult.recover {
      case e: UseCaseError => toFailedProcessError(e)
    }
  }

  object CirceCirceJsonOps extends CirceJsonHelper {
    import io.circe.Json
    implicit class FutureJsonOps(value: Future[Json])(implicit ec: ExecutionContext) {
      def toSuccessResponse(implicit writeable: Writeable[Json]): Future[Result] =
        value.map(v => successJson(Ok, v)).returnErrorIfExists()

      def toCreateResponse(implicit writeable: Writeable[Json]): Future[Result] =
        value.map(v => successJson(Created, v)).returnErrorIfExists()
    }
  }

  object JsonOps extends JsonHelper {
    implicit class FutureSeqOps[T](value: Future[Seq[T]])(implicit ec: ExecutionContext) {
      def toSuccessResponse(implicit writes: Writes[Seq[T]]): Future[Result] =
        value
          .map(v => successJson(Ok, Json.toJson(v)))
          .returnErrorIfExists()
    }
  }
}
