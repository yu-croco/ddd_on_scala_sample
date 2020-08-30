package adapter.controllers.monster

import adapter.controllers.JsonOps.FutureSeqOps
import com.google.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import query.monster.MonsterFindAllQuery

import scala.concurrent.ExecutionContext

class MonstersController @Inject()(cc: ControllerComponents, monsterFindAllQuery: MonsterFindAllQuery)(
    implicit ec: ExecutionContext)
    extends AbstractController(cc) {
  def index(): Action[AnyContent] = Action.async { implicit request =>
    monsterFindAllQuery.findAll().toSuccessResponse
  }
}
