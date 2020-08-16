name := "sample"
 
version := "1.0" 
      
lazy val `sample` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.12.11"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)