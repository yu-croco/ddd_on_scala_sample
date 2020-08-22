package domain.hunter

import scala.concurrent.Future

trait HunterRepository {
  def findById(id: HunterId): Future[Option[Hunter]]
}
