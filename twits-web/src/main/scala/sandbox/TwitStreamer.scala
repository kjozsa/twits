package sandbox

import twitter4j._

object TwitStreamer extends App {
  System.setProperty("twitter4j.loggerFactory", "twitter4j.NullLoggerFactory")

  val twitter = TwitterFactory.getSingleton()
  val stream = new TwitterStreamFactory(twitter.getConfiguration).getInstance()

  stream.addListener(new StatusAdapter {
    override def onStatus(status: Status): Unit = {
      if (status.getText.toLowerCase.contains("ukraine"))
        println(s"${status.getLang} # ${status.getUser.getScreenName}: ${status.getText}")
    }
  })

  stream.sample()

}
