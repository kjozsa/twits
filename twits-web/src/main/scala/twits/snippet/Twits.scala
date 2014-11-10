package twits.snippet

import net.liftweb.http.SHtml
import net.liftweb.util.Helpers._
import twitter4j.{Paging, TwitterFactory}

import scala.collection.JavaConversions._
import scala.language.{implicitConversions, postfixOps}
import scala.xml._

class Twits {
  val twitter = TwitterFactory.getSingleton()

  var pageSize = 10

  def timeline = Stream from (1) flatMap { page => twitter.getUserTimeline(new Paging(page))}

  def prettify(message: String): NodeSeq = message.split(" ").foldLeft(NodeSeq.Empty) { (acc, s) =>
    val node = s match {
      case s if s.startsWith("#") => <span class="bg-success">
        {s}
      </span>
      case s if s.startsWith("@") => <span class="bg-danger">
        {s}
      </span>
      case s => Text(s)
    }
    acc ++ node ++ Text(" ")
  }

  def render = {
    SHtml.idMemoize(outer =>
      ".twit *" #> timeline.view.take(pageSize).zipWithIndex.map { case (twit, idx) =>
        ".num" #> (1 + idx) &
          ".message *" #> prettify(twit.getText)
      } &
        ".more" #> SHtml.a(() => { pageSize += 10; outer.setHtml() }, <p>load more</p>)
    )
  }
}
