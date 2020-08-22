package infrastructure.repositoryimpl

import domain.monster.{Monster, MonsterId, MonsterRepository}

import scala.concurrent.Future

class MonsterRepositoryImpl extends BaseRepositoryImpl with MonsterRepository {
  import profile.api._

  override def findById(id: MonsterId): Future[Option[Monster]] = ???

  override def update(monster: Monster): Future[Monster] = ???
}
