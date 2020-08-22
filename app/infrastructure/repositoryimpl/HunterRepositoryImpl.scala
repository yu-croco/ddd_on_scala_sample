package infrastructure.repositoryimpl

import domain.hunter.{Hunter, HunterId, HunterRepository}
import dto.Tables.Hunters

import scala.concurrent.Future

class HunterRepositoryImpl extends BaseRepositoryImpl with HunterRepository {
  import profile.api._

  override def findById(id: HunterId): Future[Option[Hunter]] =
    for {
      hunterR <- db.run(Hunters.filter(_.id === id.value).result.headOption)
    } yield hunterR.map(_.toModel)
}
