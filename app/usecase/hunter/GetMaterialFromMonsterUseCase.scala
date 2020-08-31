package usecase.hunter

import com.google.inject.Inject
import domain.model.hunter.HunterId
import domain.model.monster.{MonsterId, MonsterMaterial}
import domain.repository.hunter.HunterRepository
import domain.repository.monster.MonsterRepository
import org.atnos.eff.Eff
import org.atnos.eff.create._future
import usecase.usecase.{_useCaseEither, EitherDomainErrorOps, EitherUCErrorOps, FutureOps, FutureOptionOps}

import scala.concurrent.ExecutionContext

class GetMaterialFromMonsterUseCase @Inject()(hunterRepository: HunterRepository, monsterRepository: MonsterRepository)(
    implicit ec: ExecutionContext
) {
  def program[R: _future: _useCaseEither](hunterId: HunterId, monsterId: MonsterId): Eff[R, MonsterMaterial] =
    for {
      hunter        <- hunterRepository.findById(hunterId).toUCErrorIfNotExists("hunter").toEff
      monster       <- monsterRepository.findById(monsterId).toUCErrorIfNotExists("monster").toEff
      takenMaterial <- hunter.getMonsterMaterial(monster).toUCErrorIfLeft().toEff
      aaa = println(hunter.id)
      _ <- hunterRepository.addMonsterMaterial(hunter, takenMaterial).raiseIfFutureFailed("monsterMaterial").toEff
    } yield takenMaterial
}
