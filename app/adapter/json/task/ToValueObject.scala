package adapter.json.task

import adapter.helper.AdapterError
import domain.task.{TaskDetail, TaskName}
import domain.user.UserId
import play.api.libs.json.{Json, Reads}
import cats.implicits._

case class CreateTaskJson(userId: Long, taskName: String, taskDetail: String)

object CreateTaskJson {
  implicit def createTaskJsonReads: Reads[CreateTaskJson] = Json.reads[CreateTaskJson]
}

case class CreateTask(userId: UserId, taskName: TaskName, taskDetail: TaskDetail)

object CreateTask {
  def convertToEntity(json: CreateTaskJson): Either[AdapterError, CreateTask] = {
    val userId     = UserId.create(json.userId).toValidatedNel
    val taskName   = TaskName.create(json.taskName).toValidatedNel
    val taskDetail = TaskDetail.create(json.taskDetail).toValidatedNel

    (userId, taskName, taskDetail)
      .mapN(CreateTask.apply)
      .toEither
      .leftMap(e => AdapterError(e.flatMap(_.detail)))
  }
}
