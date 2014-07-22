package bootstrap.liftweb

import net.liftweb._
import net.liftweb.common.{Logger, _}
import net.liftweb.http._
import twits.TwitStreamer

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Logger {

  def boot() {
    // where to search snippet
    LiftRules.addToPackages("twits")

    //    // Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    //
    //    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    TwitStreamer.bootstrap()

    LiftRules.unloadHooks append (() => TwitStreamer.shutdown())

    //    LiftRules.setSiteMapFunc(MenuInfo.sitemap)
  }

  //  object MenuInfo {
  //    def sitemap() = SiteMap(
  //      Menu("welcome") / "index",
  //      Menu("number guess!") / "numberguess",
  //      Menu("screen") / "screen",
  //      Menu("screen2") / "screen2",
  //      Menu("form") / "form",
  //      Menu("db crud") / "dbcrud",
  //      Menu("dbedit") / "dbedit" >> Hidden,
  //      Menu("ajax") / "ajax",
  //      Menu("i18n") / "i18n",
  //      Menu("discounts") / "discounts",
  //      Menu("pwchange") / "pwchange",
  //      Menu("submenus") / "submenus" submenus (
  //        Menu("first") / "sub/subfirst"
  //        )
  //    )
  //  }
}
