scalaVersion := "2.13.11"
name := "springer-drawing"


libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.5"
libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.10"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test

libraryDependencies ++= Seq( "org.junit.jupiter"% "junit-jupiter" % "5.9.2" % Test,
                             "org.mockito" % "mockito-core" % "4.8.0" % Test,
                             "org.mockito" % "mockito-junit-jupiter" % "4.8.0" % Test,
                             )