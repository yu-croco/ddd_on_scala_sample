import org.atnos.eff.{Fx, TimedFuture}
import usecase.usecase.UseCaseEither

package object adapter {
  type FutureEitherStack = Fx.fx2[TimedFuture, UseCaseEither]
}
