package domain.validations.monster

import domain.helpers.DomainError
import domain.model.monster.{Monster, MonsterMaterial}
import domain.validation.singleValidate

case class TakenMaterialValidation(monster: Monster) {
  def validate: Either[DomainError, MonsterMaterial] =
    singleValidate[MonsterMaterial](
      monster.life.isZero,
      monster.materials.head,
      DomainError.create("モンスターはまだ生きているので素材を剥ぎ取れません")
    )
}
