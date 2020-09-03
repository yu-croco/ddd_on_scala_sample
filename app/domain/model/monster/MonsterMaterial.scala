package domain.model.monster

import domain.{NonEmptyStringVODomainSpecificationFactory, NonNegativeLongVODomainSpecificationFactory}

case class MonsterMaterial(
    name: MonsterMaterialName,
    rarity: MonsterMaterialRarity
)

case class MonsterMaterialName(value: String) extends AnyVal
object MonsterMaterialName                    extends NonEmptyStringVODomainSpecificationFactory[MonsterMaterialName]

case class MonsterMaterialRarity(value: Long) extends AnyVal
object MonsterMaterialRarity                  extends NonNegativeLongVODomainSpecificationFactory[MonsterMaterialRarity]
