package adapter.json.hunter.getmaterial

import adapter.helper.AdapterError
import cats.implicits._
import domain.hunter.HunterId
import domain.monster.MonsterId
import play.api.libs.json.{Json, Reads}

case class GetMaterialFromMonsterJson(hunterId: Long, monsterId: Long)

object GetMaterialFromMonsterJson {
  implicit def jsonReads: Reads[GetMaterialFromMonsterJson] = Json.reads[GetMaterialFromMonsterJson]
}

case class GetMaterialFromMonster(hunterId: HunterId, monsterId: MonsterId)

object AttackMonsterRequest {
  def convertToEntity(json: GetMaterialFromMonsterJson): Either[AdapterError, GetMaterialFromMonster] = {
    val hunterId  = HunterId.create(json.hunterId).toValidatedNel
    val monsterId = MonsterId.create(json.monsterId).toValidatedNel

    (hunterId, monsterId)
      .mapN(GetMaterialFromMonster.apply)
      .toEither
      .leftMap(e => AdapterError(e.flatMap(_.detail)))
  }
}
