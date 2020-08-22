package infrastructure.helper.ops
import domain.hunter.{Hunter, HunterDefensePower, HunterId, HunterLife, HunterName, HunterOffensePower}
import dto.Tables.HuntersRow

trait HunterOps {
  implicit class HunterModel(row: HuntersRow) {
    def toModel = Hunter(
      HunterId(row.id),
      HunterName(row.name),
      HunterLife(row.life),
      HunterDefensePower(row.defensePower),
      HunterOffensePower(row.offensePower)
    )
  }
}
