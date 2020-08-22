package usecase.hunter

import com.google.inject.Inject
import domain.hunter.{HunterId, HunterRepository}
import domain.monster.{Monster, MonsterId, MonsterRepository}
import org.atnos.eff.Eff
import org.atnos.eff.future._future
import domain.service.HunterAttackService
import usecase.usecase.{_useCaseEither, EitherDomainErrorOps, EitherUCErrorOps, FutureOps, FutureOptionOps}

import scala.concurrent.ExecutionContext

class AttackMonsterUseCase @Inject()(hunterRepository: HunterRepository, monsterRepository: MonsterRepository)(
    implicit ec: ExecutionContext) {

  def exec[R: _useCaseEither: _future](hunterId: HunterId, monsterId: MonsterId): Eff[R, Monster] =
    for {
      hunter  <- hunterRepository.findById(hunterId).toUCErrorIfNotExists("hunter").toEff
      monster <- monsterRepository.findById(monsterId).toUCErrorIfNotExists("monster").toEff
      givenDamage = HunterAttackService.calculateDamage(hunter, monster)
      damagedMonster <- hunter.attack(monster, givenDamage).toUCErrorIfLeft.toEff
      savedMonster   <- monsterRepository.update(damagedMonster).rollbackAndRaiseIfFutureFailed("monster").toEff
    } yield savedMonster
}
