package twits.actor

import akka.actor.Actor
import net.liftweb.http.CometActor

class LiftBridgeActor extends Actor {
  private var target: Option[CometActor] = None

  def receive = {
    case comet: CometActor => target = Some(comet)
    case message => target.foreach(_ ! message)
  }
}

case class CometMessage(sender: String, message: String)

