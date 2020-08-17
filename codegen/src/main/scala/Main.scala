// see:https://scala-slick.org/doc/3.2.0-M1/code-generation.html
// run `bin/codegen` to create
import slick.codegen.SourceCodeGenerator

object Main {
  def main(args: Array[String]): Unit = {
    val slickDriver  = "slick.jdbc.PostgresProfile"
    val jdbcDriver   = "org.postgresql.Driver"
    val url          = "jdbc:postgresql://localhost:9010/scala_on_ddd_dev"
    val outputFolder = "./app/infrastructure"
    val pkg          = "dto"
    val user         = "postgres"
    val password     = "postgres"

    SourceCodeGenerator.run(
      slickDriver,
      jdbcDriver,
      url,
      outputFolder,
      pkg,
      Some(user),
      Some(password),
      true,
      true
    )
  }
}
