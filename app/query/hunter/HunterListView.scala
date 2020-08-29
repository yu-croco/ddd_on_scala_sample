package query.hunter

import play.api.libs.json.{Json, OWrites}
import dto.Tables._

case class HuntedMonsterMaterialView(
    name: String,
    rarity: Long
)
case class HunterListView(
    id: Long,
    name: String,
    life: Long,
    defencePower: Long,
    offensePower: Long,
    materials: Seq[HuntedMonsterMaterialView]
)

object HunterListView {
  implicit val materialListViewJsonWrites: OWrites[HuntedMonsterMaterialView] = Json.writes[HuntedMonsterMaterialView]
  implicit val hunterListViewJsonWrites: OWrites[HunterListView]              = Json.writes[HunterListView]

  def fromRow(monsterR: HuntersRow, materialR: Seq[MonsterMaterialsRow]): HunterListView =
    HunterListView(
      monsterR.id,
      monsterR.name,
      monsterR.life,
      monsterR.defensePower,
      monsterR.offensePower,
      materialR.map { m =>
        HuntedMonsterMaterialView(m.name, m.rarity)
      }
    )
}
