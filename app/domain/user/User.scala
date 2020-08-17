package domain.user

case class User(id: UserId, name: UserName)

case class UserId(value: Long)
object UserId

case class UserName(value: String)
object UserName
