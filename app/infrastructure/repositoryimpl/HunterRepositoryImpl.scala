package infrastructure.repositoryimpl

import domain.hunter.{Hunter, HunterId, HunterRepository}
import domain.monster.MonsterMaterial
import dto.Tables.Hunters

import scala.concurrent.Future

class HunterRepositoryImpl extends BaseRepositoryImpl with HunterRepository {
  import profile.api._

  override def findById(id: HunterId): Future[Option[Hunter]] =
    for {
      hunterR <- db.run(Hunters.filter(_.id === id.value).result.headOption)
    } yield hunterR.map(_.toModel)

  override def update(hunter: Hunter): Future[Hunter] =
    for {
      _ <- db.run(
        Hunters
          .filter(_.id === hunter.id.value)
          .map(m => (m.name, m.life, m.defensePower, m.offensePower))
          .update((hunter.name.value, hunter.life.value, hunter.defencePower.value, hunter.offensePower.value))
      )
    } yield hunter

  override def addMonsterMaterial(hunter: Hunter, monsterMaterial: MonsterMaterial): Future[Unit] = ???
}
