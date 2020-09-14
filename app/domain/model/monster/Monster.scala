package domain.model.monster

import domain.helpers.DomainError
import domain.model.hunter.{Hunter, HunterAttackDamage, HunterDefensePower, HunterOffensePower}
import domain.specs.monster.{MonsterAttackedSpec, TakenMaterialSpec}
import domain.{DomainIDFactory, LongVOFactory, StringVOFactory}

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
    MonsterAttackedSpec(this).validate(givenDamage)

  def attack(hunter: Hunter, damage: MonsterAttackDamage): Either[DomainError, Hunter] =
    hunter.attackedBy(damage)

  def takenMaterial(): Either[DomainError, MonsterMaterial] =
    TakenMaterialSpec(this).validate
}

case class MonsterId(value: String) extends AnyVal

object MonsterId extends DomainIDFactory[MonsterId] {
  def error: DomainError = DomainError.create("monsterIDの形式に誤りがあります")
}

case class MonsterName(value: String) extends AnyVal
object MonsterName extends StringVOFactory[MonsterName] {
  def error: DomainError = DomainError.create("monsterNameには1文字以上の値を入力してください")
}

case class MonsterLife(value: Long) extends AnyVal {
  def isZero                             = value == 0
  def -(givenDamage: HunterAttackDamage) = MonsterLife(this.value - givenDamage.value)
  def >=(v: Long): Boolean               = this.value >= v
  def toZero()                           = MonsterLife(0)
}

object MonsterLife extends LongVOFactory[MonsterLife] {
  def error: DomainError = DomainError.create("monsterLifeには1以上の値を入力してください")
}

case class MonsterDefensePower(value: Long) extends AnyVal {
  def >=(offense: HunterOffensePower): Boolean = this.value >= offense.value
}

object MonsterDefensePower extends LongVOFactory[MonsterDefensePower] {
  def error: DomainError = DomainError.create("monsterDefensePowerには1以上の値を入力してください")
}

case class MonsterOffensePower(value: Long) extends AnyVal {
  def -(defence: HunterDefensePower) = MonsterOffensePower(this.value - defence.value)
  def +(other: MonsterOffensePower)  = MonsterOffensePower(this.value + other.value)
}

object MonsterOffensePower extends LongVOFactory[MonsterOffensePower] {
  def error: DomainError = DomainError.create("monsterOffensePowerには1以上の値を入力してください")
}

case class MonsterAttackDamage(value: Long) extends AnyVal

object MonsterAttackDamage extends LongVOFactory[MonsterAttackDamage] {
  def error: DomainError = DomainError.create("monsterAttackDamageには1以上の値を入力してください")
}
