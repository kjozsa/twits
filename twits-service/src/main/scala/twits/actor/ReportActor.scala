package twits.actor

import akka.actor.Actor
import twits.{Message, Logger}
import twitter4j.Status

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

abstract class ReportActor(executionContext: ExecutionContext) extends Actor with Logger {
  object Report

  override def preStart(): Unit = {
    implicit val ec = executionContext
    context.system.scheduler.schedule(1 second, 1 second, self, Report)
  }

  override def receive = {
    case Message(status) => onStatus(status)
    case Report => report()
  }

  def onStatus(status: Status)

  def report()

}


