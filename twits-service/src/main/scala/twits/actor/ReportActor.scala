package twits.actor

import akka.actor.{Actor, ActorRef}
import twits.{Logger, Message}
import twitter4j.Status

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._


/**
 * base class for all reporting actors - sends reports to Lift comet actor using LiftBridgeActor
 */
abstract class ReportActor(liftBridge: ActorRef, executionContext: ExecutionContext) extends Actor with Logger {
  private object Report

  override def preStart(): Unit = {
    implicit val ec = executionContext
    context.system.scheduler.schedule(1 second, 1 second, self, Report)
  }

  override def receive = {
    case Message(status) => onStatus(status)
    case Report => liftBridge ! CometMessage(self.path.name, report())
  }

  def onStatus(status: Status)

  def report(): String
}


