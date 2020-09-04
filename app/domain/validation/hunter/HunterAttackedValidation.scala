package domain.validation.hunter

import cats.data.Validated
import domain.ValidationResult
import domain.helpers.DomainError
import domain.model.hunter.Hunter
import domain.model.monster.MonsterAttackDamage

case class HunterAttackedValidation(hunter: Hunter) {
  def validate(givenDamage: MonsterAttackDamage, test: Boolean): ValidationResult[Hunter] =
    Validated
      .cond(
        test,
        hunter.copy(life = calculateRestOfLife(givenDamage)),
        DomainError.create("hunter", "既にこのハンターは倒しています")
      )
      .toValidatedNel

  private def calculateRestOfLife(givenDamage: MonsterAttackDamage) = {
    val diff = hunter.life - givenDamage

    if (diff >= 0) diff
    else hunter.life.toZero()
  }
}
