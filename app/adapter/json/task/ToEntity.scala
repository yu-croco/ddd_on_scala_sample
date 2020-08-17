package adapter.json.task

import domain.task.{TaskDetail, TaskName}
import domain.user.UserId
import play.api.libs.json.{Json, Reads}

case class CreateTaskJson(userId: Long, taskName: String, taskDetail: String)

object CreateTaskJson {
  implicit def createTaskJsonReads: Reads[CreateTaskJson] = Json.reads[CreateTaskJson]
}

case class CreateTask(userId: UserId, taskName: TaskName, taskDetail: TaskDetail)

object CreateTask {
  def convertToEntity(json: CreateTaskJson) = CreateTask(
    UserId(json.userId),
    TaskName(json.taskName),
    TaskDetail(json.taskDetail)
  )
}
