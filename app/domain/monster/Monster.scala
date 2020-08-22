package domain.monster

import domain.hunter.HunterOffensePower
import domain.{NonEmptyStringVOFactory, NonNegativeLongVOFactory}

case class Monster(
    id: MonsterId,
    name: MonsterName,
    life: MonsterLife,
    defencePower: MonsterDefensePower,
    offensePower: MonsterOffensePower
) {
  def attackedBy(hunterOffensePower: HunterOffensePower): Monster =
    this.copy(life = calculateRestOfLife(hunterOffensePower))

  private def calculateRestOfLife(hunterOffensePower: HunterOffensePower): MonsterLife = {
    val diff = this.life.value - hunterOffensePower.value

    if (diff >= 0) MonsterLife(diff)
    else MonsterLife(0)
  }
}

case class MonsterId(value: Long) extends AnyVal
object MonsterId                  extends NonNegativeLongVOFactory[MonsterId]

case class MonsterName(value: String) extends AnyVal
object MonsterName                    extends NonEmptyStringVOFactory[MonsterName]

case class MonsterLife(value: Long) extends AnyVal
object MonsterLife                  extends NonNegativeLongVOFactory[MonsterLife]

case class MonsterDefensePower(value: Long) extends AnyVal
object MonsterDefensePower                  extends NonNegativeLongVOFactory[MonsterDefensePower]

case class MonsterOffensePower(value: Long) extends AnyVal
object MonsterOffensePower                  extends NonNegativeLongVOFactory[MonsterOffensePower]
