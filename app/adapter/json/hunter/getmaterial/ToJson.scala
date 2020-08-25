package adapter.json.hunter.getmaterial

import domain.monster.MonsterMaterial
import play.api.libs.json.{Json, Writes}
trait ToJson {
  implicit def toJson: Writes[MonsterMaterial] =
    (json: MonsterMaterial) =>
      Json.obj(
        "name"   -> json.name.value,
        "rarity" -> json.rarity.value
    )
}
