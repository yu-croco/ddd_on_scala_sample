package domain.model.monster

case class MonsterMaterial(
    name: MonsterMaterialName,
    rarity: MonsterMaterialRarity
)

case class MonsterMaterialName(value: String) extends AnyVal
object MonsterMaterialName

case class MonsterMaterialRarity(value: Long) extends AnyVal
object MonsterMaterialRarity
