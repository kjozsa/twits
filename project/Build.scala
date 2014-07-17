import sbt._
import Keys._
import com.earldouglas.xsbtwebplugin._
import WebappPlugin._

object Build extends Build {

  import BuildSettings._

  //  override lazy val settings = super.settings :+ {
  //    shellPrompt := { s => "[" + scala.Console.CYAN + Project.extract(s).currentProject.id + scala.Console.RESET + "] $ "}
  //  }

  lazy val container = Container("container")

  lazy val twits_root = Project(
    id = "twits-root",
    base = file("."),
    settings = basicSettings ++ container.deploy(
      "/" -> twits_web
    )
  ).settings(libraryDependencies ++= Dependencies.twits_root)

  lazy val twits_web = Project(
    id = "twits-web",
    base = file("twits-web"),
    settings = basicSettings ++ webappSettings
  ).settings(libraryDependencies ++= Dependencies.twits_web)

}
