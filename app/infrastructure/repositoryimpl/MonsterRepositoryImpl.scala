package infrastructure.repositoryimpl

import domain.model.monster.{Monster, MonsterId}
import domain.repository.monster.MonsterRepository
import dto.Tables
import dto.Tables.{MonsterMaterials, Monsters}

import scala.concurrent.Future

class MonsterRepositoryImpl extends BaseRepositoryImpl with MonsterRepository {
  import profile.api._

  private type MonsterResources = Seq[(Tables.MonstersRow, Tables.MonsterMaterialsRow)]

  override def findById(id: MonsterId): Future[Option[Monster]] = {
    def query() =
      Monsters
        .filter(_.id === id.value)
        .join(MonsterMaterials)
        .on {
          case (monsters, monsterMaterials) =>
            monsters.id === monsterMaterials.monsterId
        }
        .result

    def formatResult(resource: MonsterResources): Option[Monster] =
      resource.groupBy {
        case (monsters, _) => monsters
      }.toSeq.map {
        case (monstersR, resources) => monstersR.toModel(resources.map(_._2))
      }.headOption

    for {
      maybeMonster <- db.run(query())
    } yield formatResult(maybeMonster)
  }

  override def update(monster: Monster): Future[Monster] =
    for {
      _ <- db.run(
        Monsters
          .filter(_.id === monster.id.value)
          .map(m => (m.name, m.life, m.defensePower, m.offensePower))
          .update((monster.name.value, monster.life.value, monster.defencePower.value, monster.offensePower.value))
      )
    } yield monster
}
