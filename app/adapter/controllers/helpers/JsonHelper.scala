package adapter.controllers.helpers

import play.api.libs.json._
import play.api.mvc
import play.api.mvc.Results.BadRequest
import play.api.mvc.{Result, Results}

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

  def failureJson(error: collection.Seq[(JsPath, collection.Seq[JsonValidationError])]): Result =
    Results
      .BadRequest(
        Json.toJson(
          Json.obj(
            "status" -> BadRequest.header.status,
            "data"   -> error.flatMap(_._2.map(_.message))
          )
        )
      )
      .as(contentType = "application/json")
}
