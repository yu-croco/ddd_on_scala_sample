package domain.task

import scala.concurrent.Future

trait TaskRepository {
  def add(task: Task): Future[Task]
}
