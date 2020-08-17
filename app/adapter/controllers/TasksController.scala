package adapter.controllers

import adapter.controllers.helpers.JsonHelper
import adapter.json.task.{CreateTask, CreateTaskJson, ToJson}
import com.google.inject.Inject
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import usecase.task.CreateTaskUseCase

import scala.concurrent.{ExecutionContext, Future}

class TasksController @Inject()(cc: ControllerComponents, createTaskUseCase: CreateTaskUseCase)(
    implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with JsonHelper
    with ToJson {

  def create: Action[JsValue] = Action.async(parse.json) { implicit request =>
    val body = request.body.validate[CreateTaskJson]

    body.fold(
      e => Future.successful(failureJson(e)),
      value => {
        CreateTask
          .convertToEntity(value)
          .fold(
            e => Future.successful(toVOConvertError(e)),
            vo => createTaskUseCase.exec(vo.userId, vo.taskName, vo.taskDetail).createSuccessfullyResponse
          )
      }
    )
  }
}
