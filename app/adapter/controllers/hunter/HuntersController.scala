package adapter.controllers.hunter

import adapter.controllers.FutureOptionOps
import adapter.controllers.helpers.JsonHelper
import adapter.json.hunter.findquery.ToJson
import com.google.inject.Inject
import domain.hunter.HunterId
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import query.hunter.HunterFindQuery

import scala.concurrent.{ExecutionContext, Future}

class HuntersController @Inject()(cc: ControllerComponents, hunterQuery: HunterFindQuery)(implicit ec: ExecutionContext)
    extends AbstractController(cc)
    with JsonHelper
    with ToJson {

  def show(hunterId: Long): Action[AnyContent] = Action.async { implicit request =>
    HunterId
      .create(hunterId)
      .fold(
        e => Future.failed(e),
        value => hunterQuery.findById(value).toSuccessResponse
      )
  }
}
