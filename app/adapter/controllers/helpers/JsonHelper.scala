package adapter.controllers.helpers

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
  case class ErrorDetail(
      status: Int,
      error: collection.Seq[(JsPath, collection.Seq[JsonValidationError])]
  )

  implicit val requestJsonTypeErrorJsonWrites: Writes[ErrorDetail] = (request: ErrorDetail) =>
    Json.obj(
      "status" -> request.status,
      "errors" -> request.error.map {
        case (key, messages) =>
          Json.obj(
            key.toString() -> messages.map(_.message).foldRight("")(_ ++ _)
          )
      }
  )
}

case class ErrorResponse(
    status: Int,
    message: NonEmptyList[(String, String)]
)

object ErrorResponse {
  implicit val errorResponseJsonWrites: Writes[ErrorResponse] = (res: ErrorResponse) =>
    Json.obj(
      "status" -> res.status,
      "errors" -> res.message.toList.map {
        case (key, message) =>
          Json.obj(key -> message)
      }
  )
}

trait JsonHelper {
  def successJson(resultStatus: mvc.Results.Status, value: JsValue): Result =
    resultStatus(
      Json.toJson(
        Json.obj(
          "status" -> resultStatus.header.status,
          "data"   -> value
        )
      )
    ).as(contentType = "application/json")

  def toRequestJsonTypeError(error: collection.Seq[(JsPath, collection.Seq[JsonValidationError])]): Result =
    Results
      .BadRequest(
        Json.toJson(ErrorDetail(BadRequest.header.status, error))
      )
      .as(contentType = "application/json")

  def toVOConvertError(e: AdapterError): Result =
    Results
      .Status(Status.BAD_REQUEST)(
        Json.toJson(
          ErrorResponse(
            Status.BAD_REQUEST,
            e.detail
          )
        )
      )
      .as(contentType = "application/json")

  def toFailedProcessError(e: UseCaseError): Result =
    Results
      .Status(Status.BAD_REQUEST)(
        Json.toJson(
          ErrorResponse(
            Status.BAD_REQUEST,
            e.error
          )
        )
      )
      .as(contentType = "application/json")
}

trait CirceJsonHelper {
  import io.circe.Json
  import io.circe.syntax.EncoderOps

  def successJson(resultStatus: mvc.Results.Status, value: Json)(implicit writeable: Writeable[Json]): Result =
    resultStatus(
      Json.obj(
        "status" -> resultStatus.header.status.asJson,
        "data"   -> value
      )
    ).as(contentType = "application/json")
}
