package domain.monster

import domain.{NonEmptyStringVOFactory, NonNegativeLongVOFactory}

case class MonsterMaterial(
    name: MonsterMaterialName,
    rarity: MonsterMaterialRarity
)

case class MonsterMaterialName(value: String) extends AnyVal
object MonsterMaterialName                    extends NonEmptyStringVOFactory[MonsterMaterialName]

case class MonsterMaterialRarity(value: Long) extends AnyVal
object MonsterMaterialRarity                  extends NonNegativeLongVOFactory[MonsterMaterialRarity]
