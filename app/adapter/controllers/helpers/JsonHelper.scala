package adapter.controllers.helpers

import domain.task.Task
import play.api.libs.json._
import play.api.mvc.Results.Ok
import play.api.mvc.{Result, Results}

trait JsonHelper {
  def successJson(value: JsValue): Result =
    Results
      .Ok(
        Json.toJson(
          Json.obj(
            "status" -> Ok.header.status,
            "data"   -> value
          )
        )
      )
      .as(contentType = "application/json")

  def failureJson(e: collection.Seq[(JsPath, collection.Seq[JsonValidationError])]): Result =
    Results
      .Ok(
        Json.toJson(
          Json.obj(
            "status" -> Ok.header.status,
            "data"   -> e.flatMap(_._2.map(_.message))
          )
        )
      )
      .as(contentType = "application/json")
}
