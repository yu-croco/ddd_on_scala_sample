package domain.service

import domain.hunter.{Hunter, HunterAttackDamage}
import domain.monster.Monster

// 計算ロジックそのものは適当
object HunterAttackService {
  def calculateDamage(hunter: Hunter, monster: Monster): HunterAttackDamage = {
    val defence = monster.defencePower.value
    val offense = hunter.offensePower.value

    if (defence >= offense) HunterAttackDamage(offense)
    else HunterAttackDamage(defence + (offense - defence))
  }
}
