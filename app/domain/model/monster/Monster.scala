package domain.model.monster

import domain.helper.DomainValidationError
import domain.model.hunter.{Hunter, HunterAttackDamage, HunterDefensePower, HunterOffensePower}
import domain.{NonEmptyStringVOFactory, NonNegativeLongVOFactory}

case class Monster(
    id: MonsterId,
    name: MonsterName,
    life: MonsterLife,
    defencePower: MonsterDefensePower,
    offensePower: MonsterOffensePower,
    attackDamage: Option[MonsterAttackDamage] = None,
    materials: Seq[MonsterMaterial]
) {
  def attackedBy(givenDamage: HunterAttackDamage): Either[DomainValidationError, Monster] =
    if (this.life.isZero) Left(DomainValidationError.create("monster", "既にこのモンスターは倒しています"))
    else Right(this.copy(life = calculateRestOfLife(givenDamage)))

  def attack(hunter: Hunter, damage: MonsterAttackDamage) =
    hunter.attackedBy(damage)

  def takenMaterial(): Either[DomainValidationError, MonsterMaterial] =
    if (!this.life.isZero) Left(DomainValidationError.create("monster", "まだ生きているので素材を剥ぎ取れません"))
    else Right(this.materials.head)

  private def calculateRestOfLife(givenDamage: HunterAttackDamage): MonsterLife = {
    val diff = this.life - givenDamage

    if (diff >= 0) diff
    else this.life.toZero()
  }
}

case class MonsterId(value: Long) extends AnyVal
object MonsterId                  extends NonNegativeLongVOFactory[MonsterId]

case class MonsterName(value: String) extends AnyVal
object MonsterName                    extends NonEmptyStringVOFactory[MonsterName]

case class MonsterLife(value: Long) extends AnyVal {
  def isZero                             = value == 0
  def -(givenDamage: HunterAttackDamage) = MonsterLife(this.value - givenDamage.value)
  def >=(v: Long): Boolean               = this.value >= v
  def toZero()                           = MonsterLife(0)
}
object MonsterLife extends NonNegativeLongVOFactory[MonsterLife]

case class MonsterDefensePower(value: Long) extends AnyVal {
  def >=(offense: HunterOffensePower): Boolean = this.value >= offense.value
}
object MonsterDefensePower extends NonNegativeLongVOFactory[MonsterDefensePower]

case class MonsterOffensePower(value: Long) extends AnyVal {
  def -(defence: HunterDefensePower) = MonsterOffensePower(this.value - defence.value)
  def +(other: MonsterOffensePower)  = MonsterOffensePower(this.value + other.value)
}
object MonsterOffensePower extends NonNegativeLongVOFactory[MonsterOffensePower]

case class MonsterAttackDamage(value: Long) extends AnyVal
object MonsterAttackDamage                  extends NonNegativeLongVOFactory[MonsterAttackDamage]
