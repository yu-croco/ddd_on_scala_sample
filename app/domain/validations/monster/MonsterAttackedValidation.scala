package domain.validations.monster

import domain.helpers.DomainError
import domain.model.hunter.HunterAttackDamage
import domain.model.monster.Monster
import domain.validation.singleValidate

case class MonsterAttackedValidation(monster: Monster) {
  def validate(givenDamage: HunterAttackDamage): Either[DomainError, Monster] =
    singleValidate[Monster](
      !monster.life.isZero,
      monster.copy(life = calculateRestOfLife(givenDamage)),
      DomainError.create("このモンスターは既に倒しています")
    )

  private def calculateRestOfLife(givenDamage: HunterAttackDamage) = {
    val diff = monster.life - givenDamage

    if (diff >= 0) diff
    else monster.life.toZero()
  }
}
