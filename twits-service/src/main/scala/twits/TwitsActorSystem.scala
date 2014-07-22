package twits

import akka.actor._
import twits.actor.{Counter, Sampler}
import twitter4j.Status


class TwitsActorSystem {
  val system = ActorSystem.create("twits")

  system.eventStream.subscribe(system.actorOf(Props[Sampler]), classOf[Message])
  system.eventStream.subscribe(system.actorOf(Props(new Counter(system.dispatcher))), classOf[Message])

  def publish(message: Message) {
    system.eventStream.publish(message)
  }
}

case class Message(status: Status)
