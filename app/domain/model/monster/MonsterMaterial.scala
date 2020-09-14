package domain.model.monster

import domain.helpers.DomainError
import domain.{LongVOFactory, StringVOFactory}

case class MonsterMaterial(
    name: MonsterMaterialName,
    rarity: MonsterMaterialRarity
)

case class MonsterMaterialName(value: String) extends AnyVal

object MonsterMaterialName extends StringVOFactory[MonsterMaterialName] {
  def error: DomainError = DomainError.create("monsterMaterialNameには1文字以上の値を入力してください")
}

case class MonsterMaterialRarity(value: Long) extends AnyVal
object MonsterMaterialRarity extends LongVOFactory[MonsterMaterialRarity] {
  override def error: DomainError = DomainError.create("monsterMaterialRarityには1以上の値を入力してください")
}
