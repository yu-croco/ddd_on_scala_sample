package adapter.json.task

import domain.task.Task
import play.api.libs.json.{Json, Writes}

trait ToJson {
  implicit def taskJsonWrites: Writes[Task] = (task: Task) => {
    Json.obj(
      "userId"     -> task.userId.value,
      "taskId"     -> task.id.map(_.value),
      "taskName"   -> task.name.value,
      "taskDetail" -> task.detail.value
    )
  }
}
