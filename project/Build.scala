import sbt._
import Keys._
import com.earldouglas.xsbtwebplugin._
import WebappPlugin._

object Build extends Build {

  import BuildSettings._

  lazy val container = Container("container")

  lazy val twits_root = Project(
    id = "twits-root",
    base = file("."),
    settings = basicSettings ++ container.deploy(
      "/" -> twits_web
    )
  )
    .settings(libraryDependencies ++= Dependencies.twits_root)
    .aggregate(twits_service, twits_web)

  lazy val twits_service = Project(
    id = "twits-service",
    base = file("twits-service"),
    settings = basicSettings
  ).settings(libraryDependencies ++= Dependencies.twits_service)

  lazy val twits_web = Project(
    id = "twits-web",
    base = file("twits-web"),
    settings = basicSettings ++ webappSettings
  )
    .settings(libraryDependencies ++= Dependencies.twits_web)
    .dependsOn(twits_service)

}
