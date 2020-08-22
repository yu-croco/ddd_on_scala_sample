package domain.service

import domain.hunter.Hunter
import domain.monster.Monster

// 計算ロジックそのものは適当
object HunterAttackService {
  def calculateDamage(hunter: Hunter, monster: Monster): Long = {
    val defence = monster.defencePower.value
    val offense = hunter.offensePower.value

    if (defence >= offense) offense
    else defence + (offense - defence)
  }
}
