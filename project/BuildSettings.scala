import sbt._
import Keys._

object BuildSettings {

  lazy val basicSettings = Seq(
    version := "1.0.0",
    organization := "org.dyn.twits",

    scalaVersion := "2.10.4",

    scalacOptions := Seq(
      "-encoding", "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-language:postfixOps",
      "-language:implicitConversions"
    )
  )
}