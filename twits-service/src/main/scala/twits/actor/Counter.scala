package twits.actor

import twitter4j.Status

import scala.concurrent.ExecutionContext

class Counter(executionContext: ExecutionContext) extends ReportActor(executionContext) {
  var count = 0

  def onStatus(status: Status) {
    count += 1
  }

  def report() {
      logger.info("{} messages per second", count)
      count = 0
  }
}