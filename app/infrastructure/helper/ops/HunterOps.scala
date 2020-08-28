package infrastructure.helper.ops
import domain.hunter._
import dto.Tables._

trait HunterOps {
  implicit class HunterModel(row: HuntersRow) {
    def toModel(materials: Seq[MonsterMaterialsRow] = Seq()) = Hunter(
      HunterId(row.id),
      HunterName(row.name),
      HunterLife(row.life),
      HunterDefensePower(row.defensePower),
      HunterOffensePower(row.offensePower),
      materials.map { m =>
        HuntedMonsterMaterial(HuntedMonsterMaterialName(m.name), HuntedMonsterMaterialRarity(m.rarity))
      }
    )
  }
}
