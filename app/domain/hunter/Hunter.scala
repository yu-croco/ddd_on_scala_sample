package domain.hunter

import domain.helper.DomainValidationError
import domain.monster.{Monster, MonsterDefensePower}
import domain.{NonEmptyStringVOFactory, NonNegativeLongVOFactory}

case class Hunter(
    id: HunterId,
    name: HunterName,
    life: HunterLife,
    defencePower: HunterDefensePower,
    offensePower: HunterOffensePower,
    attackDamage: Option[HunterAttackDamage] = None
) {
  def attack(monster: Monster, givenDamage: HunterAttackDamage): Either[DomainValidationError, Monster] =
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

case class HunterOffensePower(value: Long) extends AnyVal {
  def -(defence: MonsterDefensePower) = HunterOffensePower(this.value - defence.value)
  def +(that: HunterOffensePower)     = HunterOffensePower(this.value + that.value)
}

object HunterOffensePower extends NonNegativeLongVOFactory[HunterOffensePower]

case class HunterAttackDamage(value: Long) extends AnyVal
object HunterAttackDamage                  extends NonNegativeLongVOFactory[HunterAttackDamage]
