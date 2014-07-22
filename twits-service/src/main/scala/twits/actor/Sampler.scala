package twits.actor

import akka.actor.Actor
import twits.{Message, Logger}

class Sampler extends Actor with Logger {
  override def receive = {
    case Message(status) if status.getText.toLowerCase.contains("twit") => logger.debug(status.getText)
  }
}
