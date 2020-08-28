package adapter.json.hunter.findquery

import domain.hunter.Hunter
import play.api.libs.json.{Json, Writes}

trait ToJson {
  implicit def toJson: Writes[Option[Hunter]] =
    (json: Option[Hunter]) =>
      Json.obj(
        "id"           -> json.map(_.id.value),
        "name"         -> json.map(_.name.value),
        "life"         -> json.map(_.life.value),
        "offensePower" -> json.map(_.offensePower.value),
        "defensePower" -> json.map(_.defencePower.value),
        "huntedMonsterMaterials" -> json.map {
          _.huntedMaterials.map { m =>
            Json.obj(
              "name"   -> m.name.value,
              "rarity" -> m.rarity.value
            )
          }
        }
    )
}
