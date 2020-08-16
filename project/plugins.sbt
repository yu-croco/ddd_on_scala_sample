logLevel := Level.Warn

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.2")
addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.4.0")
libraryDependencies += "org.postgresql"      % "postgresql"      % "42.2.14"