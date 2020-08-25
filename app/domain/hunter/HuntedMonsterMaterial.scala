package domain.hunter

import domain.{NonEmptyStringVOFactory, NonNegativeLongVOFactory}

case class HuntedMonsterMaterial(
    name: HuntedMonsterMaterialName,
    rarity: HuntedMonsterMaterialRarity
)

case class HuntedMonsterMaterialName(value: String) extends AnyVal
object HuntedMonsterMaterialName                    extends NonEmptyStringVOFactory[HuntedMonsterMaterialName]

case class HuntedMonsterMaterialRarity(value: Long) extends AnyVal
object HuntedMonsterMaterialRarity                  extends NonNegativeLongVOFactory[HuntedMonsterMaterialRarity]
