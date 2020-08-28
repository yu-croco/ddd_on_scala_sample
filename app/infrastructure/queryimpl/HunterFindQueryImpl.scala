package infrastructure.queryimpl

import domain.hunter.{Hunter, HunterId}
import dto.Tables.{Hunters, HuntersMonsterMaterials, MonsterMaterials}
import query.HunterFindQuery

import scala.concurrent.Future

class HunterFindQueryImpl extends BaseQueryImpl with HunterFindQuery {
  import profile.api._

  override def findById(hunterId: HunterId): Future[Option[Hunter]] =
    for {
      hunterR <- db.run(Hunters.filter(_.id === hunterId.value).result.headOption)
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
}
