package infrastructure.ops

import domain.model.monster._
import dto.Tables.{MonsterMaterialsRow, MonstersRow}

trait MonsterOps {
  implicit class MonsterModel(row: MonstersRow) {
    def toModel(materials: Seq[MonsterMaterialsRow]): Monster = Monster(
      MonsterId(row.id),
      MonsterName(row.name),
      MonsterLife(row.life),
      MonsterDefensePower(row.defensePower),
      MonsterOffensePower(row.offensePower),
      None,
      materials.map { material =>
        MonsterMaterial(MonsterMaterialName(material.name), MonsterMaterialRarity(material.rarity))
      }
    )
  }
}
