package adapter.json.monster.attack

import adapter.helper.AdapterError
import cats.implicits._
import domain.hunter.HunterId
import domain.monster.MonsterId
import play.api.libs.json.{Json, Reads}

case class AttackHunterJson(hunterId: Long, monsterId: Long)

object AttackHunterJson {
  implicit def jsonReads: Reads[AttackHunterJson] = Json.reads[AttackHunterJson]
}

case class AttackHunter(hunterId: HunterId, monsterId: MonsterId)

object AttackHunterRequest {
  def convertToEntity(json: AttackHunterJson): Either[AdapterError, AttackHunter] = {
    val hunterId  = HunterId.create(json.hunterId).toValidatedNel
    val monsterId = MonsterId.create(json.monsterId).toValidatedNel

    (hunterId, monsterId)
      .mapN(AttackHunter.apply)
      .toEither
      .leftMap(e => AdapterError(e.flatMap(_.detail)))
  }
}
