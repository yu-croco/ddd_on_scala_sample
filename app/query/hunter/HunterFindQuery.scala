package query.hunter

import domain.hunter.{Hunter, HunterId}

import scala.concurrent.Future

trait HunterFindQuery {
  def findById(hunterId: HunterId): Future[Option[Hunter]]
}
