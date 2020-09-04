package domain.model.hunter

import domain.helpers.DomainError
import domain.model.monster._
import domain.{EntityIdFactory, NonEmptyStringVOFactory, NonNegativeLongVOFactory, Specification}

case class Hunter(
    id: HunterId,
    name: HunterName,
    life: HunterLife,
    defencePower: HunterDefensePower,
    offensePower: HunterOffensePower,
    huntedMaterials: Seq[HuntedMonsterMaterial],
    attackDamage: Option[HunterAttackDamage] = None
) {
  def attack(monster: Monster, givenDamage: HunterAttackDamage): Either[DomainError, Monster] =
    monster.attackedBy(givenDamage)

  def attackedBy(givenDamage: MonsterAttackDamage): Either[DomainError, Hunter] =
    if (this.life.isZero()) Left(DomainError.create("hunter", "既にこのハンターは倒しています"))
    else Right(this.copy(life = calculateRestOfLife(givenDamage)))

  def getMonsterMaterial(monster: Monster): Either[DomainError, MonsterMaterial] = monster.takenMaterial()

  private def calculateRestOfLife(givenDamage: MonsterAttackDamage) = {
    val diff = this.life - givenDamage

    if (diff >= 0) diff
    else this.life.toZero()
  }
}

case class HunterId(value: String) extends AnyVal
object HunterId                    extends EntityIdFactory[HunterId]

case class HunterName(value: String) extends AnyVal
object HunterName                    extends NonEmptyStringVOFactory[HunterName]

case class HunterLife(value: Long) extends AnyVal {
  def isZero(): Boolean              = this.value == 0
  def -(damage: MonsterAttackDamage) = HunterLife(this.value - damage.value)
  def >=(v: Long): Boolean           = this.value >= v
  def toZero(): HunterLife           = HunterLife(0)
}
object HunterLife extends Specification[Long, HunterLife] {
  override def error: DomainError = DomainError.create("hunterLife", "Noneやめて！")

  override def test(t: Long): Boolean = t >= 0
}

case class HunterDefensePower(value: Long) extends AnyVal {
  def >=(offense: MonsterOffensePower): Boolean = this.value >= offense.value
}
object HunterDefensePower extends NonNegativeLongVOFactory[HunterDefensePower]

case class HunterOffensePower(value: Long) extends AnyVal {
  def -(defence: MonsterDefensePower) = HunterOffensePower(this.value - defence.value)
  def +(that: HunterOffensePower)     = HunterOffensePower(this.value + that.value)
}

object HunterOffensePower extends NonNegativeLongVOFactory[HunterOffensePower]

case class HunterAttackDamage(value: Long) extends AnyVal
object HunterAttackDamage                  extends NonNegativeLongVOFactory[HunterAttackDamage]
