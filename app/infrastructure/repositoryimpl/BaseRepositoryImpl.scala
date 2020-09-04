package infrastructure.repositoryimpl

import infrastructure.ops.{Ops, UuidGeneratorOps}
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.PostgresProfile

trait BaseRepositoryImpl extends HasDatabaseConfigProvider[PostgresProfile] with Ops with UuidGeneratorOps
