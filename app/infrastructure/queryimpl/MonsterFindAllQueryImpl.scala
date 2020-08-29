package infrastructure.queryimpl

import dto.Tables
import dto.Tables.{MonsterMaterials, Monsters}
import query.monster.{MonsterFindAllQuery, MonsterListView}

import scala.concurrent.Future

class MonsterFindAllQueryImpl extends BaseQueryImpl with MonsterFindAllQuery {
  import profile.api._

  override def findAll(): Future[Seq[MonsterListView]] =
    for {
      dbResult <- db.run(
        Monsters
          .joinLeft(MonsterMaterials)
          .on { case (monsters, materials) => monsters.id === materials.monsterId }
          .result)
    } yield formatResponse(dbResult)

  private type DbResult = Seq[(Tables.MonstersRow, Option[Tables.MonsterMaterialsRow])]

  private def formatResponse(dbResult: DbResult): Seq[MonsterListView] =
    dbResult.groupBy { case (monsters, _) => monsters }.toSeq.map {
      case (monsterR, resources) => {
        val materialsR = resources.collect {
          case (_, materials) if materials.isDefined => materials.get
        }
        MonsterListView.fromRow(monsterR, materialsR)
      }
    }
}
