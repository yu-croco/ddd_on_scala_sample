package infrastructure.repositoryimpl

import com.google.inject.Inject
import infrastructure.helper.ops.Ops
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.PostgresProfile

import scala.concurrent.ExecutionContext

trait BaseRepositoryImpl extends HasDatabaseConfigProvider[PostgresProfile] with Ops {
  @Inject() private var ecc: ExecutionContext = _
  implicit lazy val ec: ExecutionContext      = ecc

  @Inject() private var dbConf: DatabaseConfigProvider                          = _
  implicit override protected lazy val dbConfigProvider: DatabaseConfigProvider = dbConf
}
