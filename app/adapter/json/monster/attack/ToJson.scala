package adapter.json.monster.attack

import domain.hunter.Hunter
import play.api.libs.json.{Json, Writes}

trait ToJson {
  implicit def toJson: Writes[Hunter] = (json: Hunter) => {
    Json.obj(
      "id"           -> json.id.value,
      "name"         -> json.name.value,
      "life"         -> json.life.value,
      "offensePower" -> json.offensePower.value,
      "defensePower" -> json.defencePower.value
    )
  }
}
