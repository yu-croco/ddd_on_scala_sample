package domain.specs.hunter

import domain.helpers.DomainError
import domain.model.hunter.Hunter
import domain.model.monster.MonsterAttackDamage
import domain.validation.singleValidate

case class HunterAttackedSpec(hunter: Hunter) {
  def validate(givenDamage: MonsterAttackDamage): Either[DomainError, Hunter] =
    singleValidate[Hunter](
      !hunter.life.isZero,
      hunter.copy(life = calculateRestOfLife(givenDamage)),
      DomainError.create("このハンターは既に倒しています")
    )

  private def calculateRestOfLife(givenDamage: MonsterAttackDamage) = {
    val diff = hunter.life - givenDamage

    if (diff >= 0) diff
    else hunter.life.toZero()
  }
}
