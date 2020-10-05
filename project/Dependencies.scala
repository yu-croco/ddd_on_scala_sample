import play.sbt.PlayImport._
import sbt._

object Dependencies {
  object Versions {
    val akkaVersion  = "5.0.0"
    val circeVersion = "0.12.3"
  }

  val playDependencies = Seq(ehcache, ws, specs2 % Test, guice)

  val scalatestDependencies = Seq(
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
  )

  val otherDependencies = Seq(
    "org.postgresql"       % "postgresql"             % "42.2.14",
    "com.typesafe.play"    %% "play-slick"            % Versions.akkaVersion,
    "com.typesafe.play"    %% "play-slick-evolutions" % Versions.akkaVersion,
    "com.typesafe.slick"   %% "slick-codegen"         % "3.3.2",
    "org.joda"             % "joda-convert"           % "2.2.1",
    "com.github.tototoshi" %% "slick-joda-mapper"     % "2.4.1",
    "org.typelevel"        %% "cats-core"             % "2.1.1",
    "net.codingwell"       %% "scala-guice"           % "4.2.11",
    "org.atnos"            %% "eff"                   % "5.10.0"
  )

  val circeDependencies = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % Versions.circeVersion)
  val playCirceDependencies = Seq(
    "com.dripower" %% "play-circe" % "2812.0"
  )

  val allLibraryDependencies = playDependencies ++ scalatestDependencies ++
    otherDependencies ++ circeDependencies ++ playCirceDependencies

  val codegenDependencies = Seq(
    "com.typesafe.slick"  %% "slick-codegen"  % "3.3.2",
    "org.postgresql"      % "postgresql"      % "42.2.14",
    "com.github.tminglei" %% "slick-pg"       % "0.19.0",
    "ch.qos.logback"      % "logback-classic" % "1.2.3" % Runtime
  )

  val akkaResolvers = Seq(
    "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
  )
  val allResolvers = akkaResolvers
}
