package domain.hunter

import domain.helper.DomainValidationError
import domain.monster.Monster
import domain.{NonEmptyStringVOFactory, NonNegativeLongVOFactory}

case class Hunter(
    id: HunterId,
    name: HunterName,
    life: HunterLife,
    defencePower: HunterDefensePower,
    offensePower: HunterOffensePower
) {
  def attack(monster: Monster, givenDamage: Long): Either[DomainValidationError, Monster] =
    monster.attackedBy(givenDamage)
}

case class HunterId(value: Long) extends AnyVal
object HunterId                  extends NonNegativeLongVOFactory[HunterId]

case class HunterName(value: String) extends AnyVal
object HunterName                    extends NonEmptyStringVOFactory[HunterName]

case class HunterLife(value: Long) extends AnyVal
object HunterLife                  extends NonNegativeLongVOFactory[HunterLife]

case class HunterDefensePower(value: Long) extends AnyVal
object HunterDefensePower                  extends NonNegativeLongVOFactory[HunterDefensePower]

case class HunterOffensePower(value: Long) extends AnyVal
object HunterOffensePower                  extends NonNegativeLongVOFactory[HunterOffensePower]
