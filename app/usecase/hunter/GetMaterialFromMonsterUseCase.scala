package usecase.hunter

import com.google.inject.Inject
import domain.hunter.{HunterId, HunterRepository}
import domain.monster.{MonsterId, MonsterMaterial, MonsterRepository}
import org.atnos.eff.Eff
import org.atnos.eff.create._future
import usecase.usecase.{_useCaseEither, EitherDomainErrorOps, EitherUCErrorOps, FutureOps, FutureOptionOps}

import scala.concurrent.ExecutionContext

class GetMaterialFromMonsterUseCase @Inject()(hunterRepository: HunterRepository, monsterRepository: MonsterRepository)(
    implicit ec: ExecutionContext
) {
  def run[R: _useCaseEither: _future](hunterId: HunterId, monsterId: MonsterId): Eff[R, MonsterMaterial] =
    for {
      hunter        <- hunterRepository.findById(hunterId).toUCErrorIfNotExists("hunter").toEff
      monster       <- monsterRepository.findById(monsterId).toUCErrorIfNotExists("monster").toEff
      takenMaterial <- hunter.getMonsterMaterial(monster).toUCErrorIfLeft().toEff
      _             <- hunterRepository.addMonsterMaterial(hunter, takenMaterial).raiseIfFutureFailed("monsterMaterial").toEff
    } yield takenMaterial
}
