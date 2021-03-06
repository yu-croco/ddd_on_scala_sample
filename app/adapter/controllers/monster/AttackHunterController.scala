package adapter.controllers.monster

import adapter.controllers.CirceCirceJsonOps.FutureJsonOps
import adapter.controllers.FutureEitherStack
import adapter.controllers.helpers.JsonHelper
import adapter.json.monster.attack.{AttackHunterJson, AttackHunterRequest, ToJson}
import com.google.inject.Inject
import io.circe.generic.auto._
import io.circe.syntax._
import org.atnos.eff.ExecutorServices
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.either._
import org.atnos.eff.syntax.future._
import play.api.libs.circe._
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import usecase.helpers.UseCaseError
import usecase.monster.AttackHunterUseCase

import scala.concurrent.{ExecutionContext, Future}

class AttackHunterController @Inject()(cc: ControllerComponents, useCase: AttackHunterUseCase)(
    implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with JsonHelper
    with ToJson
    with Circe {

  def update(monsterId: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext
    val body                          = request.body.validate[AttackHunterJson]

    body.fold(
      e => Future.successful(e.toRequestJsonTypeError),
      value =>
        AttackHunterRequest
          .convertToEntity(value, monsterId)
          .fold(
            e => Future.successful(e.toVOConvertError),
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
