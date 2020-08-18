package domain.user

import domain.NonNegativeLongVOFactory
import domain.NonEmptyStringVOFactory

case class User(id: UserId, name: UserName)

case class UserId(value: Long) extends AnyVal
object UserId                  extends NonNegativeLongVOFactory[UserId]

case class UserName(value: String) extends AnyVal
object UserName                    extends NonEmptyStringVOFactory[UserName]
