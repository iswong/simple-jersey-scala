name := "simple-jersey-scala"

organization := "momo"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.glassfish.jersey.core" % "jersey-server" % "2.22.2",
  "org.glassfish.jersey.containers" % "jersey-container-grizzly2-http" % "2.22.2",
  "org.glassfish.jersey.media" % "jersey-media-sse" % "2.22.2",
  "org.apache.logging.log4j" % "log4j-api" % "2.5",
  "org.apache.logging.log4j" % "log4j-core" % "2.5",
  "org.apache.logging.log4j" % "log4j-jul" % "2.5",
  "org.glassfish.jersey.test-framework" % "jersey-test-framework-core" % "2.22.2" % "test",
  "org.glassfish.jersey.test-framework.providers" % "jersey-test-framework-provider-inmemory" % "2.22.2" % "test",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)

