package query.hunter

import scala.concurrent.Future

trait HunterFindAllQuery {
  def findAll(): Future[Seq[HunterListView]]
}
