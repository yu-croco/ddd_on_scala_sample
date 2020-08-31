package usecase.monster

import domain.model.hunter.{Hunter, HunterId}
import domain.model.monster.MonsterId
import domain.repository.hunter.HunterRepository
import domain.repository.monster.MonsterRepository
import domain.service.monster.MonsterAttackService
import javax.inject.Inject
import org.atnos.eff.Eff
import org.atnos.eff.create._future
import usecase.usecase.{_useCaseEither, EitherDomainErrorOps, EitherUCErrorOps, FutureOps, FutureOptionOps}

import scala.concurrent.ExecutionContext

class AttackHunterUseCase @Inject()(hunterRepository: HunterRepository, monsterRepository: MonsterRepository)(
    implicit ec: ExecutionContext) {
  def program[R: _future: _useCaseEither](hunterId: HunterId, monsterId: MonsterId): Eff[R, Hunter] =
    for {
      hunter  <- hunterRepository.findById(hunterId).toUCErrorIfNotExists("hunter").toEff
      monster <- monsterRepository.findById(monsterId).toUCErrorIfNotExists("monster").toEff
      monsterAttackDamage = MonsterAttackService.calculateDamage(monster, hunter)
      damagedHunter <- monster.attack(hunter, monsterAttackDamage).toUCErrorIfLeft().toEff
      savedHunter   <- hunterRepository.update(damagedHunter).raiseIfFutureFailed("hunter").toEff
    } yield savedHunter
}
