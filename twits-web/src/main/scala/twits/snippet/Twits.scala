package twits.snippet

import net.liftweb.util.Helpers._
import twitter4j.{Paging, TwitterFactory}

import scala.collection.JavaConversions._
import scala.language.{implicitConversions, postfixOps}
import scala.xml._

class Twits {
  val twitter = TwitterFactory.getSingleton()

  def timeline = Stream from (1) takeWhile (_ <= 2) flatMap { page => twitter.getUserTimeline(new Paging(page))}

  def prettify(message: String): NodeSeq = message.split(" ").foldLeft(NodeSeq.Empty) { (acc, s) =>
    val node = s match {
      case s if s.startsWith("#") => <span class="bg-success">{s}</span>
      case s if s.startsWith("@") => <span class="bg-danger">{s}</span>
      case s => Text(s)
    }
    acc ++ node ++ Text(" ")
  }

  def render = {
    ".twit *" #> timeline.view.zipWithIndex.map { case (twit, idx) =>
      ".num" #> (1 + idx) &
      ".message *" #> prettify(twit.getText)
    }
  }
}
