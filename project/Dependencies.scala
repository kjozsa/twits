import sbt._

object Version {

  val scala = "2.11.1"
  val akka = "2.3.4"
  val jetty = "9.2.1.v20140609"
  val liftweb = "2.6-M4"
  val twitter4j = "4.0.2"
  val servlet_api = "3.0.1"
  val logback = "1.1.2"
}

object Library {
  val akka_actor = "com.typesafe.akka" %% "akka-actor" % Version.akka
  val akka_contrib = "com.typesafe.akka" %% "akka-contrib" % Version.akka

  val lift = "net.liftweb" %% "lift-webkit" % Version.liftweb
}

object Dependencies {

  import Library._

  val twits_root = List(
    "org.eclipse.jetty" % "jetty-webapp" % Version.jetty % "container"
  )

  val twits_service = List(
    "org.twitter4j" % "twitter4j-core" % Version.twitter4j,
    "org.twitter4j" % "twitter4j-stream" % Version.twitter4j
  )

  val twits_web = List(
    "net.liftweb" %% "lift-webkit" % Version.liftweb,
    "javax.servlet" % "javax.servlet-api" % Version.servlet_api % "provided",
    "ch.qos.logback" % "logback-classic" % Version.logback
  )
}