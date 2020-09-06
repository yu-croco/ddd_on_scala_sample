package adapter.json.monster.attack

import adapter.helper.AdapterError
import cats.data.Validated
import cats.implicits._
import domain.model.hunter.HunterId
import domain.model.monster.MonsterId
import play.api.libs.json.{Json, Reads}

case class AttackHunterJson(hunterId: String)

object AttackHunterJson {
  implicit def jsonReads: Reads[AttackHunterJson] = Json.reads[AttackHunterJson]
}

case class AttackHunter(hunterId: HunterId, monsterId: MonsterId)

object AttackHunterRequest {
  def convertToEntity(json: AttackHunterJson, monsterId: String): Validated[AdapterError, AttackHunter] = {
    val hunterId = HunterId.create(json.hunterId)
    val mId      = MonsterId.create(monsterId)

    (hunterId, mId)
      .mapN(AttackHunter.apply)
      .leftMap(e => AdapterError.create(e.flatMap(_.detail)))
  }
}
