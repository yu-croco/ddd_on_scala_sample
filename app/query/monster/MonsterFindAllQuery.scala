package query.monster

import scala.concurrent.Future

trait MonsterFindAllQuery {
  def findAll(): Future[Seq[MonsterListView]]
}
