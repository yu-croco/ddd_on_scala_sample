import com.google.inject.AbstractModule
import java.time.Clock

import domain.task.TaskRepository
import domain.user.UserRepository
import infrastructure.repositoryimpl.{TaskRepositoryImpl, UserRepositoryImpl}
import net.codingwell.scalaguice.ScalaModule

/**
  * This class is a Guice module that tells Guice how to bind several
  * different types. This Guice module is created when the Play
  * application starts.

  * Play will automatically use any class called `Module` that is in
  * the root package. You can create modules in other locations by
  * adding `play.modules.enabled` settings to the `application.conf`
  * configuration file.
  */
class Module extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)

    bind[TaskRepository].to[TaskRepositoryImpl]
    bind[UserRepository].to[UserRepositoryImpl]
  }

}
