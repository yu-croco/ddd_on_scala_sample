package adapter.json.monster.attack
import domain.hunter._
import io.circe.Encoder

trait ToJson {
  implicit val hunterIdEncoder: Encoder[HunterId]                          = Encoder[Long].contramap(_.value)
  implicit val hunterNameEncoder: Encoder[HunterName]                      = Encoder[String].contramap(_.value)
  implicit val hunterLifeEncoder: Encoder[HunterLife]                      = Encoder[Long].contramap(_.value)
  implicit val hunterOffensePowerEncoder: Encoder[HunterOffensePower]      = Encoder[Long].contramap(_.value)
  implicit val hunterDefensePowerEncoder: Encoder[HunterDefensePower]      = Encoder[Long].contramap(_.value)
  implicit val materialNameEncoder: Encoder[HuntedMonsterMaterialName]     = Encoder[String].contramap(_.value)
  implicit val materialRarityEncoder: Encoder[HuntedMonsterMaterialRarity] = Encoder[Long].contramap(_.value)
  implicit val attackDamageEncoder: Encoder[HunterAttackDamage]            = Encoder[Long].contramap(_.value)
}
