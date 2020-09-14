package infrastructure.queryimpl

import infrastructure.helpers.ops.Ops
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.PostgresProfile

trait BaseQueryImpl extends HasDatabaseConfigProvider[PostgresProfile] with Ops
