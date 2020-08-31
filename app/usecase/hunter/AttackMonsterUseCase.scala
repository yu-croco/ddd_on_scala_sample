package usecase.hunter

import com.google.inject.Inject
import domain.model.hunter.HunterId
import domain.model.monster.{Monster, MonsterId}
import domain.repository.hunter.HunterRepository
import domain.repository.monster.MonsterRepository
import domain.service.hunter.HunterAttackService
import org.atnos.eff.Eff
import org.atnos.eff.future._future
import usecase.usecase.{_useCaseEither, EitherDomainErrorOps, EitherUCErrorOps, FutureOps, FutureOptionOps}

import scala.concurrent.ExecutionContext

class AttackMonsterUseCase @Inject()(hunterRepository: HunterRepository, monsterRepository: MonsterRepository)(
    implicit ec: ExecutionContext) {

  def program[R: _future: _useCaseEither](hunterId: HunterId, monsterId: MonsterId): Eff[R, Monster] =
    for {
      hunter  <- hunterRepository.findById(hunterId).toUCErrorIfNotExists("hunter").toEff
      monster <- monsterRepository.findById(monsterId).toUCErrorIfNotExists("monster").toEff
      hunterAttackDamage = HunterAttackService.calculateDamage(hunter, monster)
      damagedMonster <- hunter.attack(monster, hunterAttackDamage).toUCErrorIfLeft().toEff
      savedMonster   <- monsterRepository.update(damagedMonster).raiseIfFutureFailed("monster").toEff
    } yield savedMonster
}
