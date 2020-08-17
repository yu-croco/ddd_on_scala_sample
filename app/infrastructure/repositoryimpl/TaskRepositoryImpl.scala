package infrastructure.repositoryimpl

import domain.task.{Task, TaskRepository}
import dto.Tables.Tasks

import scala.concurrent.Future

class TaskRepositoryImpl extends BaseRepositoryImpl with TaskRepository {
  import profile.api._

  override def add(task: Task): Future[Task] =
    for {
      task <- db.run(
        Tasks.returning(Tasks) += task.toRow
      )
    } yield task.toModel
}
