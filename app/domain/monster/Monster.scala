package domain.monster

import domain.helper.DomainValidationError
import domain.hunter.HunterAttackDamage
import domain.{NonEmptyStringVOFactory, NonNegativeLongVOFactory}

case class Monster(
    id: MonsterId,
    name: MonsterName,
    life: MonsterLife,
    defencePower: MonsterDefensePower,
    offensePower: MonsterOffensePower,
    attackDamage: Option[MonsterAttackDamage] = None
) {
  def attackedBy(givenDamage: HunterAttackDamage): Either[DomainValidationError, Monster] =
    if (this.life.isZero) Left(DomainValidationError.create("monster", "既にモンスターは倒しています"))
    else Right(this.copy(life = calculateRestOfLife(givenDamage)))

  private def calculateRestOfLife(givenDamage: HunterAttackDamage): MonsterLife = {
    val diff = this.life.value - givenDamage.value

    if (diff >= 0) MonsterLife(diff)
    else MonsterLife(0)
  }
}

case class MonsterId(value: Long) extends AnyVal
object MonsterId                  extends NonNegativeLongVOFactory[MonsterId]

case class MonsterName(value: String) extends AnyVal
object MonsterName                    extends NonEmptyStringVOFactory[MonsterName]

case class MonsterLife(value: Long) extends AnyVal {
  def isZero = value == 0
}
object MonsterLife extends NonNegativeLongVOFactory[MonsterLife]

case class MonsterDefensePower(value: Long) extends AnyVal
object MonsterDefensePower                  extends NonNegativeLongVOFactory[MonsterDefensePower]

case class MonsterOffensePower(value: Long) extends AnyVal
object MonsterOffensePower                  extends NonNegativeLongVOFactory[MonsterOffensePower]

case class MonsterAttackDamage(value: Long) extends AnyVal
object MonsterAttackDamage                  extends NonNegativeLongVOFactory[MonsterAttackDamage]
