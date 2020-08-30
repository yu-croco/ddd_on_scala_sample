package adapter.json.hunter.attack
import adapter.helper.AdapterError
import cats.data.Validated
import cats.implicits._
import domain.hunter.HunterId
import domain.monster.MonsterId
import play.api.libs.json.{Json, Reads}

case class AttackMonsterJson(monsterId: Long)

object AttackMonsterJson {
  implicit def jsonReads: Reads[AttackMonsterJson] = Json.reads[AttackMonsterJson]
}

case class AttackMonster(hunterId: HunterId, monsterId: MonsterId)

object AttackMonsterRequest {
  def convertToEntity(json: AttackMonsterJson, hunterId: Long): Validated[AdapterError, AttackMonster] = {
    val hId       = HunterId.createNel(hunterId)
    val monsterId = MonsterId.createNel(json.monsterId)

    (hId, monsterId)
      .mapN(AttackMonster.apply)
      .leftMap(e => AdapterError(e.flatMap(_.detail)))
  }
}
