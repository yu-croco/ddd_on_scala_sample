package adapter.controllers

import adapter.controllers.helpers.JsonHelper
import adapter.json.task.{CreateTask, CreateTaskJson, ToJson}
import com.google.inject.Inject
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.either._
import org.atnos.eff.syntax.future._
import org.atnos.eff.{ExecutorServices, Fx, TimedFuture}
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import usecase.helper.UseCaseError
import usecase.task.CreateTaskUseCase
import usecase.usecase.UseCaseEither

import scala.concurrent.{ExecutionContext, Future}

class TasksController @Inject()(cc: ControllerComponents, createTaskUseCase: CreateTaskUseCase)(
    implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with JsonHelper
    with ToJson {

  def create: Action[JsValue] = Action.async(parse.json) { implicit request =>
    implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext
    val body                          = request.body.validate[CreateTaskJson]

    body.fold(
      e => Future.successful(toRequestJsonTypeError(e)),
      value => {
        CreateTask
          .convertToEntity(value)
          .fold(
            e => Future.successful(toVOConvertError(e)),
            vo =>
              createTaskUseCase
                .exec[Fx.fx2[UseCaseEither, TimedFuture]](vo.userId, vo.taskName, vo.taskDetail)
                .runEither[UseCaseError]
                .runAsync
                .flatMap {
                  case Right(value) => Future.successful(value)
                  case Left(e)      => Future.failed(e)
                }
                .toCreateResponse
          )
      }
    )
  }
}
