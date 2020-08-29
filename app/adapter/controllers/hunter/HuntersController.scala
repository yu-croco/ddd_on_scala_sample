package adapter.controllers.hunter

import adapter.controllers.FutureSeqOps
import com.google.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import query.hunter.HunterFindAllQuery

import scala.concurrent.ExecutionContext

class HuntersController @Inject()(cc: ControllerComponents, hunterFinaAllQuery: HunterFindAllQuery)(
    implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  def index(): Action[AnyContent] = Action.async { implicit request =>
    hunterFinaAllQuery.findAll().toSuccessResponse
  }
}
