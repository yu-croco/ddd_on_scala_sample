package domain.task

import domain.user.{User, UserId}

case class Task(
    id: Option[TaskId],
    name: TaskName,
    detail: TaskDetail,
    userId: UserId
)

object Task {
  def create(user: User, taskName: TaskName, taskDetail: TaskDetail) =
    Task(None, taskName, taskDetail, user.id)
}

case class TaskId(value: Long) extends AnyVal
object TaskId

case class TaskName(value: String) extends AnyVal
object TaskName

case class TaskDetail(value: String) extends AnyVal
object TaskDetail
