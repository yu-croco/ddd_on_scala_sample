package domain.model.hunter

import domain.helpers.DomainError
import domain.model.monster._
import domain.specs.hunter.HunterAttackedSpec
import domain.{DomainIDFactory, LongVOFactory, StringVOFactory}

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
    HunterAttackedSpec(this).validate(givenDamage)

  def getMonsterMaterial(monster: Monster): Either[DomainError, MonsterMaterial] = monster.takenMaterial()
}

case class HunterId(value: String) extends AnyVal

object HunterId extends DomainIDFactory[HunterId] {
  def error: DomainError = DomainError.create("hunterIdの形式に誤りがあります")
}

case class HunterName(value: String) extends AnyVal

object HunterName extends StringVOFactory[HunterName] {
  def error: DomainError = DomainError.create("hunterNameには1文字以上の値を入力してください")
}

case class HunterLife(value: Long) extends AnyVal {
  def isZero: Boolean                = this.value == 0
  def -(damage: MonsterAttackDamage) = HunterLife(this.value - damage.value)
  def >=(v: Long): Boolean           = this.value >= v
  def >(v: Long): Boolean            = this.value > v
  def toZero(): HunterLife           = HunterLife(0)
}

object HunterLife extends LongVOFactory[HunterLife] {
  override def error: DomainError = DomainError.create("hunterLifeには1以上の値を入力してください")
}

case class HunterDefensePower(value: Long) extends AnyVal {
  def >=(offense: MonsterOffensePower): Boolean = this.value >= offense.value
}

object HunterDefensePower extends LongVOFactory[HunterDefensePower] {
  override def error: DomainError = DomainError.create("hunterDefensePowerには1以上の値を入力してください")
}

case class HunterOffensePower(value: Long) extends AnyVal {
  def -(defence: MonsterDefensePower) = HunterOffensePower(this.value - defence.value)
  def +(that: HunterOffensePower)     = HunterOffensePower(this.value + that.value)
}

object HunterOffensePower extends LongVOFactory[HunterOffensePower] {
  override def error: DomainError = DomainError.create("hunterOffensePowerには1以上の値を入力してください")
}

case class HunterAttackDamage(value: Long) extends AnyVal

object HunterAttackDamage extends LongVOFactory[HunterAttackDamage] {
  override def error: DomainError = DomainError.create("hunterAttackDamageには1以上の値を入力してください")
}
