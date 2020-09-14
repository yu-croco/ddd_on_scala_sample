package domain.model.hunter

import domain.{LongVOFactory, StringVOFactory}
import domain.helpers.DomainError

case class HuntedMonsterMaterial(
    name: HuntedMonsterMaterialName,
    rarity: HuntedMonsterMaterialRarity
)

case class HuntedMonsterMaterialName(value: String) extends AnyVal
object HuntedMonsterMaterialName extends StringVOFactory[HuntedMonsterMaterialName] {
  def error: DomainError = DomainError.create("huntedMonsterMaterialNameには1文字以上の値を入力してください")
}

case class HuntedMonsterMaterialRarity(value: Long) extends AnyVal

object HuntedMonsterMaterialRarity extends LongVOFactory[HuntedMonsterMaterialRarity] {
  override def error: DomainError = DomainError.create("huntedMonsterMaterialRarityには1以上の値を入力してください")
}
