package adapter.controllers.helpers

import adapter.controllers.RequestJsonTypeError
import adapter.controllers.helpers.RequestJsonTypeError.ErrorDetail
import adapter.helper.AdapterError
import cats.data.NonEmptyList
import play.api.http.{Status, Writeable}
import play.api.libs.json._
import play.api.mvc
import play.api.mvc.Results.BadRequest
import play.api.mvc.{Result, Results}
import usecase.helper.UseCaseError

object RequestJsonTypeError {
  case class ErrorDetail(error: RequestJsonTypeError)

  implicit val requestJsonTypeErrorJsonWrites: Writes[ErrorDetail] = (request: ErrorDetail) =>
    Json.obj(
      "errors" -> request.error.map {
        case (key, messages) =>
          Json.obj(
            key.toString() -> messages.map(_.message).foldRight("")(_ ++ _)
          )
      }
  )
}

case class ErrorResponse(
    message: NonEmptyList[(String, String)]
)

object ErrorResponse {
  implicit val errorResponseJsonWrites: Writes[ErrorResponse] = (res: ErrorResponse) =>
    Json.obj(
      "errors" -> res.message.toList.map {
        case (key, message) =>
          Json.obj(key -> message)
      }
  )
}

trait JsonHelper {
  def successJson(resultStatus: mvc.Results.Status, value: JsValue): Result =
    resultStatus(value).as(contentType = "application/json")

  def toRequestJsonTypeError(error: RequestJsonTypeError): Result =
    Results
      .BadRequest(Json.toJson(ErrorDetail(error)))
      .as(contentType = "application/json")

  def toVOConvertError(e: AdapterError): Result =
    Results
      .Status(Status.BAD_REQUEST)(Json.toJson(ErrorResponse(e.detail)))
      .as(contentType = "application/json")

  def toFailedProcessError(e: UseCaseError): Result =
    Results
      .Status(Status.BAD_REQUEST)(Json.toJson(ErrorResponse(e.error)))
      .as(contentType = "application/json")
}

trait CirceJsonHelper {
  import io.circe.Json

  def successJson(resultStatus: mvc.Results.Status, value: Json)(implicit writeable: Writeable[Json]): Result =
    resultStatus(value).as(contentType = "application/json")
}
