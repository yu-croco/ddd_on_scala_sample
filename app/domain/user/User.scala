package domain.user

case class User(id: UserId, name: UserName)

case class UserId(value: Long) extends AnyVal
object UserId

case class UserName(value: String) extends AnyVal
object UserName
