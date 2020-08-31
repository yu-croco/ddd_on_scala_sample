package domain.service.hunter

import domain.model.hunter.{Hunter, HunterAttackDamage, HunterOffensePower}
import domain.model.monster.{Monster, MonsterDefensePower}

object HunterAttackService {
  def calculateDamage(hunter: Hunter, monster: Monster): HunterAttackDamage =
    if (monster.defencePower >= hunter.offensePower) HunterAttackDamage(hunter.offensePower.value)
    else calc(hunter.offensePower, monster.defencePower)

  private def calc(offense: HunterOffensePower, defence: MonsterDefensePower) =
    HunterAttackDamage(
      (offense + offense - defence).value
    )
}
