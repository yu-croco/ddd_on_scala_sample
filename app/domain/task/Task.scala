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

case class TaskId(value: Long)
object TaskId

case class TaskName(value: String)
object TaskName

case class TaskDetail(value: String)
object TaskDetail
