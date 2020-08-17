package infrastructure.repositoryimpl

import cats.implicits.catsSyntaxOptionId
import domain.user.{User, UserId, UserName, UserRepository}

import scala.concurrent.Future

class UserRepositoryImpl extends BaseRepositoryImpl with UserRepository {
  override def findById(userId: UserId): Future[Option[User]] =
    Future.successful(User(UserId(1), UserName("yuki")).some)
}
