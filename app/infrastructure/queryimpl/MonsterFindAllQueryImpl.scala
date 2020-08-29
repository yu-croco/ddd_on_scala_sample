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
          .join(MonsterMaterials)
          .on { case (monsters, materials) => monsters.id === materials.monsterId }
          .result)
    } yield formatResponse(dbResult)

  private type DbResult = Seq[(Tables.MonstersRow, Tables.MonsterMaterialsRow)]

  private def formatResponse(dbResult: DbResult): Seq[MonsterListView] =
    dbResult
      .groupBy(_._1)
      .toSeq
      .map {
        case (monsterR, resources) => {
          val materialsR = resources.map(_._2)
          MonsterListView.fromRow(monsterR, materialsR)
        }
      }
}
