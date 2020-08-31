package domain.service.monster

import domain.model.hunter.{Hunter, HunterDefensePower}
import domain.model.monster.{Monster, MonsterAttackDamage, MonsterOffensePower}

object MonsterAttackService {
  def calculateDamage(monster: Monster, hunter: Hunter): MonsterAttackDamage =
    if (hunter.defencePower >= monster.offensePower) MonsterAttackDamage(monster.offensePower.value)
    else calc(monster.offensePower, hunter.defencePower)

  private def calc(offense: MonsterOffensePower, defence: HunterDefensePower) =
    MonsterAttackDamage(
      (offense + offense - defence).value
    )
}
