package domain.service

import domain.hunter.{Hunter, HunterAttackDamage, HunterOffensePower}
import domain.monster.{Monster, MonsterDefensePower}

object HunterAttackService {
  def calculateDamage(hunter: Hunter, monster: Monster): HunterAttackDamage =
    if (monster.defencePower >= hunter.offensePower) HunterAttackDamage(hunter.offensePower.value)
    else calc(hunter.offensePower, monster.defencePower)

  private def calc(offense: HunterOffensePower, defence: MonsterDefensePower) =
    HunterAttackDamage(
      (offense + offense - defence).value
    )
}
