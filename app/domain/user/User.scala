package domain.user

import domain.LongFactory.NonNegativeLongFactory
import domain.StringFactory.NonEmptyStringFactory

case class User(id: UserId, name: UserName)

case class UserId(value: Long) extends AnyVal
object UserId                  extends NonNegativeLongFactory[UserId]

case class UserName(value: String) extends AnyVal
object UserName                    extends NonEmptyStringFactory[UserName]
