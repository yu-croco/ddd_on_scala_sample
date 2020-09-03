package domain.model.hunter

import domain.{NonEmptyStringVODomainSpecificationFactory, NonNegativeLongVODomainSpecificationFactory}

case class HuntedMonsterMaterial(
    name: HuntedMonsterMaterialName,
    rarity: HuntedMonsterMaterialRarity
)

case class HuntedMonsterMaterialName(value: String) extends AnyVal
object HuntedMonsterMaterialName                    extends NonEmptyStringVODomainSpecificationFactory[HuntedMonsterMaterialName]

case class HuntedMonsterMaterialRarity(value: Long) extends AnyVal
object HuntedMonsterMaterialRarity                  extends NonNegativeLongVODomainSpecificationFactory[HuntedMonsterMaterialRarity]
