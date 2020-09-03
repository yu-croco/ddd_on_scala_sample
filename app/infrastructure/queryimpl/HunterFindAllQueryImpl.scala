package infrastructure.queryimpl

import com.google.inject.Inject
import dto.Tables
import dto.Tables.{Hunters, HuntersMonsterMaterials, MonsterMaterials}
import play.api.db.slick.DatabaseConfigProvider
import query.hunter.{HunterFindAllQuery, HunterListView}

import scala.concurrent.{ExecutionContext, Future}

class HunterFindAllQueryImpl @Inject()(private var ecc: ExecutionContext, private var dbConf: DatabaseConfigProvider)(
    implicit val dbConfigProvider: DatabaseConfigProvider,
    val ec: ExecutionContext)
    extends BaseQueryImpl
    with HunterFindAllQuery {
  import profile.api._

  override def findAll(): Future[Seq[HunterListView]] =
    for {
      dbResult <- db.run(query())
    } yield formatToResponse(dbResult)

  type DbResult = Seq[(Tables.HuntersRow, Option[Tables.MonsterMaterialsRow])]

  def formatToResponse(dbResult: DbResult): Seq[HunterListView] =
    dbResult.groupBy { case (huntersR, _) => huntersR }.toSeq.map {
      case (hunters, resources) => {
        val materials = resources.collect {
          case (_, materials) if materials.isDefined => materials.get
        }
        HunterListView.fromRow(hunters, materials)
      }
    }

  private def query() =
    Hunters
      .joinLeft(HuntersMonsterMaterials)
      .joinLeft(MonsterMaterials)
      .on {
        case ((hunters, middleTable), materials) =>
          hunters.id === middleTable.map(_.hunterId) &&
            middleTable.map(_.monsterMaterialsId) === materials.id
      }
      .map {
        case ((hunters, _), materials) => (hunters, materials)
      }
      .result

}
