package infrastructure.queryimpl

import com.google.inject.Inject
import dto.Tables
import dto.Tables.{MonsterMaterials, Monsters}
import play.api.db.slick.DatabaseConfigProvider
import query.monster.{MonsterFindAllQuery, MonsterListView}

import scala.concurrent.{ExecutionContext, Future}

class MonsterFindAllQueryImpl @Inject()(private var ecc: ExecutionContext, private var dbConf: DatabaseConfigProvider)(
    implicit val dbConfigProvider: DatabaseConfigProvider,
    val ec: ExecutionContext)
    extends BaseQueryImpl
    with MonsterFindAllQuery {
  import profile.api._

  override def findAll(): Future[Seq[MonsterListView]] =
    for {
      dbResult <- db.run(query())
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

  private def query() =
    Monsters
      .joinLeft(MonsterMaterials)
      .on { case (monsters, materials) => monsters.id === materials.monsterId }
      .result
}
