package infrastructure.helper.ops.rowpos

import cats.implicits.catsSyntaxOptionId
import domain.task.{Task, TaskDetail, TaskId, TaskName}
import domain.user.UserId
import dto.Tables
import dto.Tables.TasksRow

trait TaskRowOps {
  implicit class TaskRow(task: Task) {
    def toRow: Tables.TasksRow = TasksRow(
      0,
      task.name.value,
      task.detail.value,
      task.userId.value
    )
  }

  implicit class TaskModel(taskR: TasksRow) {
    def toModel: Task = Task(
      TaskId(taskR.id).some,
      TaskName(taskR.name),
      TaskDetail(taskR.detail),
      UserId(taskR.userId)
    )
  }
}
