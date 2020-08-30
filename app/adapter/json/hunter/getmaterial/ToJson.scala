package adapter.json.hunter.getmaterial

import domain.monster.{MonsterMaterialName, MonsterMaterialRarity}
import io.circe.Encoder
trait ToJson {
  implicit val materialNameEncoder: Encoder[MonsterMaterialName]     = Encoder[String].contramap(_.value)
  implicit val materialRarityEncoder: Encoder[MonsterMaterialRarity] = Encoder[Long].contramap(_.value)
}
