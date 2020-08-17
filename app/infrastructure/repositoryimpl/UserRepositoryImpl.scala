package infrastructure.repositoryimpl
import domain.user.{User, UserId, UserRepository}
import dto.Tables.Users

import scala.concurrent.Future

class UserRepositoryImpl extends BaseRepositoryImpl with UserRepository {
  import profile.api._

  override def findById(userId: UserId): Future[Option[User]] =
    for {
      dbResult <- db.run(Users.filter(_.id === userId.value).result.headOption)
    } yield dbResult.map(_.toModel)
}
