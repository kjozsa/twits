package twits.actor

import akka.actor.Actor
import twits.{Logger, Message}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class Counter(executionContext: ExecutionContext) extends Actor with Logger {
  var count = 0

  override def preStart(): Unit = {
    implicit val ec = executionContext
    context.system.scheduler.schedule(1 second, 1 second, self, LogCount)
  }

  override def receive = {
    case Message(status) => count += 1

    case LogCount => {
      logger.info("{} messages per second", count)
      count = 0
    }
  }
}

object LogCount