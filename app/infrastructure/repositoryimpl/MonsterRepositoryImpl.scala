package infrastructure.repositoryimpl

import domain.monster.{Monster, MonsterId, MonsterRepository}
import dto.Tables.Monsters

import scala.concurrent.Future

class MonsterRepositoryImpl extends BaseRepositoryImpl with MonsterRepository {
  import profile.api._

  override def findById(id: MonsterId): Future[Option[Monster]] =
    for {
      maybeMonster <- db.run(Monsters.filter(_.id === id.value).result.headOption)
    } yield maybeMonster.map(_.toModel)

  override def update(monster: Monster): Future[Monster] =
    for {
      _ <- db.run(
        Monsters
          .filter(_.id === monster.id.value)
          .map(m => m.life)
          .update(monster.life.value)
      )
    } yield monster
}
