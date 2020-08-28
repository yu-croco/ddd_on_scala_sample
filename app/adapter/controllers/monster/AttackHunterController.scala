package adapter.controllers.monster

import adapter.FutureEitherStack
import adapter.controllers.FutureOps
import adapter.controllers.helpers.JsonHelper
import adapter.json.monster.attack.{AttackHunterJson, AttackHunterRequest, ToJson}
import com.google.inject.Inject
import org.atnos.eff.ExecutorServices
import org.atnos.eff.concurrent.Scheduler
import org.atnos.eff.syntax.either._
import org.atnos.eff.syntax.future._
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import usecase.helper.UseCaseError
import usecase.monster.AttackHunterUseCase

import scala.concurrent.{ExecutionContext, Future}

class AttackHunterController @Inject()(cc: ControllerComponents, useCase: AttackHunterUseCase)(
    implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with JsonHelper
    with ToJson {

  def update(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    implicit val scheduler: Scheduler = ExecutorServices.schedulerFromGlobalExecutionContext
    val body                          = request.body.validate[AttackHunterJson]

    body.fold(
      e => Future.successful(toRequestJsonTypeError(e)),
      value => {
        AttackHunterRequest
          .convertToEntity(value)
          .fold(
            e => Future.successful(toVOConvertError(e)),
            vo =>
              useCase
                .program[FutureEitherStack](vo.hunterId, vo.monsterId)
                .runEither[UseCaseError]
                .runAsync
                .flatMap {
                  case Right(value) => Future.successful(value)
                  case Left(e)      => Future.failed(e)
                }
                .toSuccessResponse
          )
      }
    )
  }
}
