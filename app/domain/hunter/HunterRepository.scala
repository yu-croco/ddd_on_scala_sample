package domain.hunter

import domain.monster.MonsterMaterial

import scala.concurrent.Future

trait HunterRepository {
  def findById(id: HunterId): Future[Option[Hunter]]
  def update(hunter: Hunter): Future[Hunter]
  def addMonsterMaterial(hunter: Hunter, monsterMaterial: MonsterMaterial): Future[Unit]
}
