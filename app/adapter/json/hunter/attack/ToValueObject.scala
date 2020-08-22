package adapter.json.hunter.attack
import adapter.helper.AdapterError
import cats.implicits._
import domain.hunter.HunterId
import domain.monster.MonsterId
import play.api.libs.json.{Json, Reads}

case class AttackMonsterJson(hunterId: Long, monsterId: Long)

object AttackMonsterJson {
  implicit def jsonReads: Reads[AttackMonsterJson] = Json.reads[AttackMonsterJson]
}

case class AttackMonster(hunterId: HunterId, monsterId: MonsterId)

object AttackMonsterRequest {
  def convertToEntity(json: AttackMonsterJson): Either[AdapterError, AttackMonster] = {
    val hunterId  = HunterId.create(json.hunterId).toValidatedNel
    val monsterId = MonsterId.create(json.monsterId).toValidatedNel

    (hunterId, monsterId)
      .mapN(AttackMonster.apply)
      .toEither
      .leftMap(e => AdapterError(e.flatMap(_.detail)))
  }
}