package infrastructure.repositoryimpl

import domain.model.hunter.{Hunter, HunterId}
import domain.model.monster.MonsterMaterial
import domain.repository.hunter.HunterRepository
import dto.Tables._

import scala.concurrent.Future

class HunterRepositoryImpl extends BaseRepositoryImpl with HunterRepository {
  import profile.api._

  override def findById(id: HunterId): Future[Option[Hunter]] =
    for {
      hunterR <- db.run(Hunters.filter(_.id === id.value).result.headOption)
      materials <- db.run(
        HuntersMonsterMaterials
          .filterOpt(hunterR)(_.hunterId === _.id)
          .join(MonsterMaterials)
          .on {
            case (huntersMonsterMaterials, materials) =>
              huntersMonsterMaterials.monsterMaterialsId === materials.id
          }
          .map {
            case (_, materials) => materials
          }
          .result
      )
    } yield hunterR.map(_.toModel(materials))

  override def update(hunter: Hunter): Future[Hunter] =
    for {
      _ <- db.run(
        Hunters
          .filter(_.id === hunter.id.value)
          .map(m => (m.name, m.life, m.defensePower, m.offensePower))
          .update((hunter.name.value, hunter.life.value, hunter.defencePower.value, hunter.offensePower.value))
      )
    } yield hunter

  // ToDo: headの代わりにエラーハンドリングする
  override def addMonsterMaterial(hunter: Hunter, monsterMaterial: MonsterMaterial): Future[Unit] = {
    val id = genUUID
    db.run {
      for {
        monsterMaterialR <- MonsterMaterials.filter(_.name === monsterMaterial.name.value).result.head
        _                <- HuntersMonsterMaterials += HuntersMonsterMaterialsRow(id, hunter.id.value, monsterMaterialR.id)
      } yield ()
    }
  }
}
