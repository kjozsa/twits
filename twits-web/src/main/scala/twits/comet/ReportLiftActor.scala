package twits.comet

import net.liftweb.actor.LiftActor
import net.liftweb.http._
import net.liftweb.http.js.JsCmds._
import twits.actor.CometMessage
import twits.{Logger, TwitsActorSystem}

import scala.xml.Text

object ReportLiftActor extends LiftActor with ListenerManager {
  override protected def createUpdate = ""

}

class ReportComet extends CometActor with CometListener with Logger {
  TwitsActorSystem.liftBridge ! this

  override def lowPriority = {
    case CometMessage(sender, message) => {
      partialUpdate(SetHtml("comet-"+sender, Text(message)))
    }
  }

  override def render = {
    "#nosuch" #> <p>boo</p>
  }

  override protected def registerWith = ReportLiftActor
}

