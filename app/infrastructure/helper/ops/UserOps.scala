package infrastructure.helper.ops

import domain.user.{User, UserId, UserName}
import dto.Tables.UsersRow

trait UserOps {
  implicit class UsersModel(userR: UsersRow) {
    def toModel = User(
      UserId(userR.id),
      UserName(userR.name)
    )
  }
}
