package adapter.json.monster.attack

import adapter.helper.AdapterError
import cats.data.Validated
import cats.implicits._
import domain.model.hunter.HunterId
import domain.model.monster.MonsterId
import play.api.libs.json.{Json, Reads}

case class AttackHunterJson(hunterId: Long)

object AttackHunterJson {
  implicit def jsonReads: Reads[AttackHunterJson] = Json.reads[AttackHunterJson]
}

case class AttackHunter(hunterId: HunterId, monsterId: MonsterId)

object AttackHunterRequest {
  def convertToEntity(json: AttackHunterJson, monsterId: Long): Validated[AdapterError, AttackHunter] = {
    val hunterId = HunterId.createNel(json.hunterId)
    val mId      = MonsterId.createNel(monsterId)

    (hunterId, mId)
      .mapN(AttackHunter.apply)
      .leftMap(e => AdapterError(e.flatMap(_.detail)))
  }
}
