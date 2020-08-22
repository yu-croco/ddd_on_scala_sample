package domain.service

import domain.hunter.{Hunter, HunterAttackDamage}
import domain.monster.Monster

// 計算ロジックそのものは適当
object HunterAttackService {
  def calculateDamage(hunter: Hunter, monster: Monster): HunterAttackDamage =
    if (monster.defencePower >= hunter.offensePower) HunterAttackDamage(hunter.offensePower.value)
    else HunterAttackDamage.calc(monster.defencePower, hunter.offensePower)
}
