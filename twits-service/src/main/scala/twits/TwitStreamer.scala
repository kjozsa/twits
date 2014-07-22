package twits

import twitter4j._

object TwitStreamer extends TwitsActorSystem with Logger {
  var stream: TwitterStream = _

  def bootstrap() {
    System.setProperty("twitter4j.loggerFactory", "twitter4j.NullLoggerFactory")

    val twitter = TwitterFactory.getSingleton()
    stream = new TwitterStreamFactory(twitter.getConfiguration).getInstance()

    stream.addListener(new StatusAdapter {
      override def onStatus(status: Status): Unit = {
        publish(Message(status))
      }
    })

    logger.info("starting twitter stream")
    stream.sample()
  }

  def shutdown() {
    logger.info("Shutting down streamer")
    stream.shutdown()

    logger.info("Shutting down actor system")
    system.shutdown()
  }
}
