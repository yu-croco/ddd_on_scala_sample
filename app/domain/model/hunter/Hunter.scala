package domain.model.hunter

import domain.DomainIDFactory
import domain.helpers.DomainError
import domain.helpers.ops.DomainValidationOps._
import domain.model.monster._

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
    execWithValidation[Hunter](
      !this.life.isZero(),
      this.copy(life = calculateRestOfLife(givenDamage)),
      DomainError.create("このハンターは既に倒しています")
    )

  def getMonsterMaterial(monster: Monster): Either[DomainError, MonsterMaterial] = monster.takenMaterial()

  private def calculateRestOfLife(givenDamage: MonsterAttackDamage): HunterLife = {
    val diff = this.life - givenDamage

    if (diff >= 0) diff
    else this.life.toZero()
  }
}

case class HunterId(value: String) extends AnyVal
object HunterId extends DomainIDFactory[HunterId] {
  def error: DomainError = DomainError.create("hunterIdの形式に誤りがあります")
}

case class HunterName(value: String) extends AnyVal
object HunterName

case class HunterLife(value: Long) extends AnyVal {
  def isZero(): Boolean              = this.value == 0
  def -(damage: MonsterAttackDamage) = HunterLife(this.value - damage.value)
  def >=(v: Long): Boolean           = this.value >= v
  def >(v: Long): Boolean            = this.value > v
  def toZero(): HunterLife           = HunterLife(0)
}
object HunterLife

case class HunterDefensePower(value: Long) extends AnyVal {
  def >=(offense: MonsterOffensePower): Boolean = this.value >= offense.value
}
object HunterDefensePower

case class HunterOffensePower(value: Long) extends AnyVal {
  def -(defence: MonsterDefensePower) = HunterOffensePower(this.value - defence.value)
  def +(that: HunterOffensePower)     = HunterOffensePower(this.value + that.value)
}

object HunterOffensePower

case class HunterAttackDamage(value: Long) extends AnyVal
object HunterAttackDamage
