package twits.actor

import akka.actor.ActorRef
import twitter4j.Status

import scala.concurrent.ExecutionContext

class Counter(liftBridge: ActorRef, executionContext: ExecutionContext) extends ReportActor(liftBridge, executionContext) {
  var count = 0

  def onStatus(status: Status) {
    count += 1
  }

  def report() = {
      val message = s"${count} messages per second"
      count = 0
      message
  }
}