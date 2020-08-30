package adapter.json.hunter.attack
import adapter.helper.AdapterError
import cats.data.Validated
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
  def convertToEntity(json: AttackMonsterJson): Validated[AdapterError, AttackMonster] = {
    val hunterId  = HunterId.createNel(json.hunterId)
    val monsterId = MonsterId.createNel(json.monsterId)

    (hunterId, monsterId)
      .mapN(AttackMonster.apply)
      .leftMap(e => AdapterError(e.flatMap(_.detail)))
  }
}
