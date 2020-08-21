package usecase.task

import com.google.inject.Inject
import domain.task.{Task, TaskDetail, TaskName, TaskRepository}
import domain.user.{UserId, UserRepository}
import org.atnos.eff.Eff
import org.atnos.eff.future._future
import usecase.usecase.{_useCaseEither, FutureOps, FutureOptionOps}

import scala.concurrent.ExecutionContext

class CreateTaskUseCase @Inject()(userRepository: UserRepository, taskRepository: TaskRepository)(
    implicit ec: ExecutionContext) {

  def exec[R: _useCaseEither: _future](userId: UserId, taskName: TaskName, taskDetail: TaskDetail): Eff[R, Task] =
    for {
      user <- userRepository.findById(userId).ifNoeExists("user", "見つかりません").toEff
      unsavedTask = Task.create(user, taskName, taskDetail)
      savedTask <- taskRepository.add(unsavedTask).rollbackAndRaiseIfFutureFailed("task").toEff
    } yield savedTask
}
