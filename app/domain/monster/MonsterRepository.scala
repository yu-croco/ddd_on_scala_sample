package domain.monster

import scala.concurrent.Future

trait MonsterRepository {
  def findById(id: MonsterId): Future[Option[Monster]]
  def update(monster: Monster): Future[Monster]
}
