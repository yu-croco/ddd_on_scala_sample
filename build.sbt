import Dependencies._
name := "sample"
version := "1.0"
resolvers ++= allResolvers
libraryDependencies ++= allLibraryDependencies

inThisBuild(
  List(
    scalaVersion := "2.13.3",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
)

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard"
)

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .dependsOn(codegen)

lazy val codegen = (project in file("codegen"))
  .settings(libraryDependencies ++= codegenDependencies)
