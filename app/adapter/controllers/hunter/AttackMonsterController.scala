package adapter.controllers.hunter

import adapter.controllers.CirceCirceJsonOps.FutureJsonOps
import adapter.controllers.FutureEitherStack
import adapter.controllers.helpers.JsonHelper
import adapter.json.hunter.attack.{AttackMonsterJson, AttackMonsterRequest, ToJson}
import com.google.inject.Inject
import io.circe.generic.auto._
import io.circe.syntax._
import org.atnos.eff.ExecutorServices
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.either._
import org.atnos.eff.syntax.future._
import play.api.libs.circe.Circe
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import usecase.helper.UseCaseError
import usecase.hunter.AttackMonsterUseCase

import scala.concurrent.{ExecutionContext, Future}

class AttackMonsterController @Inject()(cc: ControllerComponents, useCase: AttackMonsterUseCase)(
    implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with JsonHelper
    with ToJson
    with Circe {

  def update(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext
    val body                          = request.body.validate[AttackMonsterJson]

    body.fold(
      e => Future.successful(toRequestJsonTypeError(e)),
      value =>
        AttackMonsterRequest
          .convertToEntity(value)
          .fold(
            e => Future.successful(toVOConvertError(e)),
            vo =>
              useCase
                .program[FutureEitherStack](vo.hunterId, vo.monsterId)
                .runEither[UseCaseError]
                .runAsync
                .flatMap {
                  case Right(value) => Future.successful(value.asJson)
                  case Left(e)      => Future.failed(e)
                }
                .toSuccessResponse
        )
    )
  }
}
