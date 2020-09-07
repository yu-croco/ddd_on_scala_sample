package domain.validation.monster

import cats.data.Validated
import domain.ValidationResult
import domain.helpers.DomainError
import domain.model.hunter.HunterAttackDamage
import domain.model.monster.Monster

case class MonsterAttackedValidation(monster: Monster) {
  def validate(givenDamage: HunterAttackDamage, test: Boolean): ValidationResult[Monster] =
    Validated
      .cond(
        test,
        monster.copy(life = calculateRestOfLife(givenDamage)),
        DomainError.create("このモンスターは既に倒しています")
      )
      .toValidatedNel

  private def calculateRestOfLife(givenDamage: HunterAttackDamage) = {
    val diff = monster.life - givenDamage

    if (diff >= 0) diff
    else monster.life.toZero()
  }
}
