package domain.model.monster

case class MonsterMaterial(
    name: MonsterMaterialName,
    rarity: MonsterMaterialRarity
)

case class MonsterMaterialName(value: String) extends AnyVal
case class MonsterMaterialRarity(value: Long) extends AnyVal
