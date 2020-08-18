package usecase.task

import com.google.inject.Inject
import domain.task.{Task, TaskDetail, TaskName, TaskRepository}
import domain.user.{UserId, UserRepository}
import usecase.usecase.{FutureOps, FutureOptionOps}

import scala.concurrent.{ExecutionContext, Future}

class CreateTaskUseCase @Inject()(userRepository: UserRepository, taskRepository: TaskRepository)(
    implicit ec: ExecutionContext) {

  def exec(userId: UserId, taskName: TaskName, taskDetail: TaskDetail): Future[Task] =
    for {
      // ToDo: Eff入れてuseCaseErrorに対応できるようにする
      user <- userRepository.findById(userId).toUseCaseError("user", "見つかりません")
      unsavedTask = Task.create(user, taskName, taskDetail)
      // ToDo: Eff入れてuseCaseErrorに対応できるようにする
      savedTask <- taskRepository.add(unsavedTask).rollbackAndRaiseIfFutureFailed("task")
    } yield savedTask
}
