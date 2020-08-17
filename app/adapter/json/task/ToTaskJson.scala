package adapter.json.task

import domain.task.Task
import play.api.libs.json.{Json, Writes}

trait ToTaskJson {
  implicit def taskJsonWrites: Writes[Task] = (task: Task) => {
    Json.obj(
      "userId"     -> task.userId.value,
      "taskName"   -> task.name.value,
      "taskDetail" -> task.detail.value
    )
  }
}
