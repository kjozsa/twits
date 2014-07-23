package twits.actor

import twitter4j.Status

import scala.collection.mutable.Map
import scala.concurrent.ExecutionContext

class HashtagCollector(executionContext: ExecutionContext) extends ReportActor(executionContext) {
  val stats = Map[String, Int]().withDefaultValue(0)

  def onStatus(status: Status) {
      status.getHashtagEntities.foreach(hashtag => stats(hashtag.getText) += 1)
  }

  def report() {
    logger.info("Top 3 hashtags: " + stats.toList.sortBy({_._2}).reverse.take(3).mkString(","))
  }

}
