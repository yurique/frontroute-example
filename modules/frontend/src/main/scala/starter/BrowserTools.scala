package starter

import frontroute._
import org.scalajs.dom

object BrowserTools {

  def setParam(paramName: String, paramValue: String): Unit = {
    val updatedParams =
      LocationUtils.encodeLocationParams(
        LocationUtils
          .parseLocationParams(dom.document.location)
          .updated(paramName, Seq(paramValue))
      )

    BrowserNavigation.pushState(url = s"${dom.document.location.pathname}$updatedParams")
  }

}
