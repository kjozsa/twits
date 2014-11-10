package twits

import akka.event.EventStream
import akka.actor._
import twits.actor.{LiftBridgeActor, HashtagCollector, Counter, Sampler}
import twitter4j.Status

class TwitsActorSystem {
  val system = ActorSystem.create("twits")
  val eventStream: EventStream = system.eventStream

  import TwitsActorSystem._

  liftBridge = system.actorOf(Props[LiftBridgeActor])

  eventStream.subscribe(system.actorOf(Props[Sampler], "sampler"), classOf[Message])
  eventStream.subscribe(system.actorOf(Props(new Counter(liftBridge, system.dispatcher)), "counter"), classOf[Message])
  eventStream.subscribe(system.actorOf(Props(new HashtagCollector(liftBridge, system.dispatcher)), "hashtagcollector"), classOf[Message])

  def publish(message: Message) {
    eventStream.publish(message)
  }
}

object TwitsActorSystem {
  var liftBridge: ActorRef = _
}

case class Message(status: Status)
