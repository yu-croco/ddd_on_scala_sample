package domain.repository.hunter

import domain.model.hunter.{Hunter, HunterId}
import domain.model.monster.MonsterMaterial

import scala.concurrent.Future

trait HunterRepository {
  def findById(id: HunterId): Future[Option[Hunter]]

  def update(hunter: Hunter): Future[Hunter]

  def addMonsterMaterial(hunter: Hunter, monsterMaterial: MonsterMaterial): Future[Unit]
}
