package query.monster

import play.api.libs.json.{Json, OWrites}
import dto.Tables._

case class MonsterMaterialView(
    name: String,
    rarity: Long
)
case class MonsterListView(
    id: Long,
    name: String,
    life: Long,
    defencePower: Long,
    offensePower: Long,
    materials: Seq[MonsterMaterialView]
)

object MonsterListView {
  implicit val materialListViewJsonWrites: OWrites[MonsterMaterialView] = Json.writes[MonsterMaterialView]
  implicit val monsterListViewJsonWrites: OWrites[MonsterListView]      = Json.writes[MonsterListView]

  def fromRow(monsterR: MonstersRow, materialR: Seq[MonsterMaterialsRow]): MonsterListView =
    MonsterListView(
      monsterR.id,
      monsterR.name,
      monsterR.life,
      monsterR.defensePower,
      monsterR.offensePower,
      materialR.map { m =>
        MonsterMaterialView(m.name, m.rarity)
      }
    )
}
