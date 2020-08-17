package domain.task

import domain.LongFactory.NonNegativeLongFactory
import domain.StringFactory.NonEmptyStringFactory
import domain.user.{User, UserId}
import org.checkerframework.checker.index.qual.NonNegative

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
object TaskId                  extends NonNegativeLongFactory[TaskId]

case class TaskName(value: String) extends AnyVal
object TaskName                    extends NonEmptyStringFactory[TaskName]

case class TaskDetail(value: String) extends AnyVal
object TaskDetail                    extends NonEmptyStringFactory[TaskDetail]
