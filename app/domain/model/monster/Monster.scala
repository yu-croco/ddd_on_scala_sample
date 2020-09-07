package domain.model.monster

import domain.DomainIDFactory
import domain.helpers.DomainError
import domain.model.hunter.{Hunter, HunterAttackDamage, HunterDefensePower, HunterOffensePower}
import domain.validation._
import domain.validation.monster.{MonsterAttackedValidation, TakenMaterialValidation}

case class Monster(
    id: MonsterId,
    name: MonsterName,
    life: MonsterLife,
    defencePower: MonsterDefensePower,
    offensePower: MonsterOffensePower,
    attackDamage: Option[MonsterAttackDamage] = None,
    materials: Seq[MonsterMaterial]
) {
  def attackedBy(givenDamage: HunterAttackDamage): Either[DomainError, Monster] =
    MonsterAttackedValidation(this).validate(givenDamage, !this.life.isZero).foldToEither()

  def attack(hunter: Hunter, damage: MonsterAttackDamage) =
    hunter.attackedBy(damage)

  def takenMaterial(): Either[DomainError, MonsterMaterial] =
    TakenMaterialValidation(this).validate().foldToEither()
}

case class MonsterId(value: String) extends AnyVal

object MonsterId extends DomainIDFactory[MonsterId] {
  def error: DomainError = DomainError.create("monsterIDの形式に誤りがあります")
}

case class MonsterName(value: String) extends AnyVal
object MonsterName

case class MonsterLife(value: Long) extends AnyVal {
  def isZero                             = value == 0
  def -(givenDamage: HunterAttackDamage) = MonsterLife(this.value - givenDamage.value)
  def >=(v: Long): Boolean               = this.value >= v
  def toZero()                           = MonsterLife(0)
}
object MonsterLife

case class MonsterDefensePower(value: Long) extends AnyVal {
  def >=(offense: HunterOffensePower): Boolean = this.value >= offense.value
}
object MonsterDefensePower

case class MonsterOffensePower(value: Long) extends AnyVal {
  def -(defence: HunterDefensePower) = MonsterOffensePower(this.value - defence.value)
  def +(other: MonsterOffensePower)  = MonsterOffensePower(this.value + other.value)
}
object MonsterOffensePower

case class MonsterAttackDamage(value: Long) extends AnyVal
object MonsterAttackDamage
