name := "sample"
version := "1.0"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

ThisBuild / scalaVersion := "2.13.3"

libraryDependencies ++= Seq(ehcache , ws , specs2 % Test , guice )

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

libraryDependencies ++= Seq(
  "org.postgresql"       % "postgresql"             % "42.2.14",
  "com.typesafe.play"    %% "play-slick"            % "5.0.0",
  "com.typesafe.play"    %% "play-slick-evolutions" % "5.0.0",
  "com.typesafe.slick"   %% "slick-codegen"         % "3.3.2",
  "org.joda"             % "joda-convert"           % "2.2.1",
  "com.github.tototoshi" %% "slick-joda-mapper"     % "2.4.1",
  "org.typelevel"        %% "cats-core"           % "2.1.1",
  "net.codingwell"       %% "scala-guice"         % "4.2.11",
  "org.atnos"            %% "eff"                 % "5.10.0"
)

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .dependsOn(codegen)

lazy val codegen = (project in file("codegen"))
  .settings(
    libraryDependencies ++= List(
      "com.typesafe.slick"  %% "slick-codegen"  % "3.3.2",
      "org.postgresql"      % "postgresql"      % "42.2.14",
      "com.github.tminglei" %% "slick-pg"       % "0.19.0",
      "ch.qos.logback"      % "logback-classic" % "1.2.3" % Runtime
    )
  )
