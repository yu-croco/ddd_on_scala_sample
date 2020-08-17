package infrastructure.repositoryimpl

import cats.implicits.catsSyntaxOptionId
import domain.task.{Task, TaskDetail, TaskId, TaskName, TaskRepository}
import domain.user.UserId

import scala.concurrent.Future

class TaskRepositoryImpl extends BaseRepositoryImpl with TaskRepository {
  override def add(task: Task): Future[Task] =
    Future.successful(Task(TaskId(1).some, TaskName("task"), TaskDetail("detail"), UserId(1L)))
}
