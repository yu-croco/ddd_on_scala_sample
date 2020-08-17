package adapter

import adapter.controllers.helpers.JsonHelper
import play.api.libs.json.{Json, Writes}
import play.api.mvc.Result

import scala.concurrent.{ExecutionContext, Future}

package object controllers extends JsonHelper {
  implicit class FutureOps[T](value: Future[T])(implicit ec: ExecutionContext) {
    def successResponse(implicit writes: Writes[T]): Future[Result] =
      value.map(v => successJson(Json.toJson(v)))
  }
}
