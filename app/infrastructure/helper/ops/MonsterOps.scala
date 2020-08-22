package infrastructure.helper.ops
import domain.monster._
import dto.Tables.MonstersRow

trait MonsterOps {
  implicit class MonsterModel(row: MonstersRow) {
    def toModel: Monster = Monster(
      MonsterId(row.id),
      MonsterName(row.name),
      MonsterLife(row.life),
      MonsterDefensePower(row.defensePower),
      MonsterOffensePower(row.offensePower)
    )
  }
}
