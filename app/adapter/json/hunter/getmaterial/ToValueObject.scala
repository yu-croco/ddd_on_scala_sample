package adapter.json.hunter.getmaterial

import adapter.helper.AdapterError
import cats.data.Validated
import cats.implicits._
import domain.model.hunter.HunterId
import domain.model.monster.MonsterId
import play.api.libs.json.{Json, Reads}

case class GetMaterialFromMonsterJson(monsterId: String)

object GetMaterialFromMonsterJson {
  implicit def jsonReads: Reads[GetMaterialFromMonsterJson] = Json.reads[GetMaterialFromMonsterJson]
}

case class GetMaterialFromMonster(hunterId: HunterId, monsterId: MonsterId)

object AttackMonsterRequest {
  def convertToEntity(json: GetMaterialFromMonsterJson,
                      hunterId: String): Validated[AdapterError, GetMaterialFromMonster] = {
    val hId       = HunterId.createNel(hunterId)
    val monsterId = MonsterId.createNel(json.monsterId)

    (hId, monsterId)
      .mapN(GetMaterialFromMonster.apply)
      .leftMap(e => AdapterError(e.flatMap(_.detail)))
  }
}
