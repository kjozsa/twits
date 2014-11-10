package twits.actor

import akka.actor.ActorRef
import twitter4j.Status

import scala.collection.mutable.Map
import scala.concurrent.ExecutionContext

class HashtagCollector(liftBridge: ActorRef, executionContext: ExecutionContext) extends ReportActor(liftBridge, executionContext) {
  val stats = Map[String, Int]().withDefaultValue(0)

  def onStatus(status: Status) {
      status.getHashtagEntities.foreach(hashtag => stats(hashtag.getText) += 1)
  }

  def report() =
    "Top 10 hashtags: " + stats.toList.sortBy({_._2}).reverse.take(10).mkString(",")

}
