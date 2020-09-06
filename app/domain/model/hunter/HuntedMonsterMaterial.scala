package domain.model.hunter

case class HuntedMonsterMaterial(
    name: HuntedMonsterMaterialName,
    rarity: HuntedMonsterMaterialRarity
)

case class HuntedMonsterMaterialName(value: String) extends AnyVal
object HuntedMonsterMaterialName

case class HuntedMonsterMaterialRarity(value: Long) extends AnyVal
object HuntedMonsterMaterialRarity
