package adapter.json.hunter.attack

import domain.monster.Monster
import play.api.libs.json.{Json, Writes}

trait ToJson {
  implicit def toJson: Writes[Monster] = (json: Monster) => {
    Json.obj(
      "id"           -> json.id.value,
      "name"         -> json.name.value,
      "life"         -> json.life.value,
      "offensePower" -> json.offensePower.value,
      "defensePower" -> json.defencePower.value
    )
  }
}
