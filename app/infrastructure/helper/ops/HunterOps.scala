package infrastructure.helper.ops
import domain.hunter.{Hunter, HunterDefensePower, HunterId, HunterLife, HunterName, HunterOffensePower}
import domain.monster.{MonsterMaterial, MonsterMaterialName, MonsterMaterialRarity}
import dto.Tables._

trait HunterOps {
  implicit class HunterModel(row: HuntersRow) {
    def toModel(materials: Seq[MonsterMaterialsRow]) = Hunter(
      HunterId(row.id),
      HunterName(row.name),
      HunterLife(row.life),
      HunterDefensePower(row.defensePower),
      HunterOffensePower(row.offensePower),
      materials.map { m =>
        MonsterMaterial(MonsterMaterialName(m.name), MonsterMaterialRarity(m.rarity))
      }
    )
  }
}
