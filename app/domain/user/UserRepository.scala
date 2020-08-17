package domain.user

import scala.concurrent.Future

trait UserRepository {
  def findById(userId: UserId): Future[Option[User]]
}
