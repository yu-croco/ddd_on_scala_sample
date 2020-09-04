package domain.validation.monster

import cats.data.Validated
import domain.ValidationResult
import domain.helpers.DomainError
import domain.model.monster.{Monster, MonsterMaterial}

case class TakenMaterialValidation(monster: Monster) {
  def validate(): ValidationResult[MonsterMaterial] =
    Validated
      .cond(
        !monster.life.isZero,
        monster.materials.head,
        DomainError.create("monster", "まだ生きているので素材を剥ぎ取れません")
      )
      .toValidatedNel
}
