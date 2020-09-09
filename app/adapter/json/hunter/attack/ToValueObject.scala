package adapter.json.hunter.attack
import adapter.helpers.AdapterError
import cats.data.Validated
import cats.implicits._
import domain.model.hunter.HunterId
import domain.model.monster.MonsterId
import play.api.libs.json.{Json, Reads}

case class AttackMonsterJson(monsterId: String)

object AttackMonsterJson {
  implicit def jsonReads: Reads[AttackMonsterJson] = Json.reads[AttackMonsterJson]
}

case class AttackMonster(hunterId: HunterId, monsterId: MonsterId)

object AttackMonsterRequest {
  def convertToEntity(json: AttackMonsterJson, hunterId: String): Validated[AdapterError, AttackMonster] = {
    val hId       = HunterId.create(hunterId)
    val monsterId = MonsterId.create(json.monsterId)

    (hId, monsterId)
      .mapN(AttackMonster.apply)
      .leftMap(e => AdapterError.create(e.flatMap(_.detail)))
  }
}
