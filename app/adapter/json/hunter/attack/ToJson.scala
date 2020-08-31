package adapter.json.hunter.attack

import domain.model.monster._
import io.circe.Encoder

trait ToJson {
  implicit val monsterIdEncoder: Encoder[MonsterId]                     = Encoder[Long].contramap(_.value)
  implicit val monsterNameEncoder: Encoder[MonsterName]                 = Encoder[String].contramap(_.value)
  implicit val monsterLifeEncoder: Encoder[MonsterLife]                 = Encoder[Long].contramap(_.value)
  implicit val monsterOffensePowerEncoder: Encoder[MonsterOffensePower] = Encoder[Long].contramap(_.value)
  implicit val monsterDefensePowerEncoder: Encoder[MonsterDefensePower] = Encoder[Long].contramap(_.value)
  implicit val materialNameEncoder: Encoder[MonsterMaterialName]        = Encoder[String].contramap(_.value)
  implicit val materialRarityEncoder: Encoder[MonsterMaterialRarity]    = Encoder[Long].contramap(_.value)
  implicit val attackDamageEncoder: Encoder[MonsterAttackDamage]        = Encoder[Long].contramap(_.value)
}
