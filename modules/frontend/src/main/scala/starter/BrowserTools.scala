package starter

import app.tulz.routing.BrowserNavigation
import org.scalajs.dom

import scala.scalajs.js

object BrowserTools {

  def setParam(paramName: String, paramValue: String): Unit = {
    val updatedParams =
      extractParams(dom.document.location)
        .updated(paramName, paramValue).map {
          case (name, value) => s"${js.URIUtils.encodeURIComponent(name)}=${js.URIUtils.encodeURIComponent(value)}"
        }.mkString("&")

    BrowserNavigation.pushState(null, null, s"${dom.document.location.pathname}?$updatedParams")
  }

  private def extractParams(location: dom.Location): Map[String, String] = {
    val vars = location.search.dropWhile(_ == '?').split('&')
    val result = scala.collection.mutable.Map[String, Seq[String]]()
    vars.foreach { entry =>
      entry.split('=') match {
        case Array(key, value) =>
          val decodedKey = js.URIUtils.decodeURIComponent(key)
          val decodedValue = js.URIUtils.decodeURIComponent(value)
          result(decodedKey) = result.getOrElse(decodedKey, Seq.empty) :+ decodedValue
        case _ =>
      }
    }
    result.toMap.view.mapValues(_.head).toMap
  }

}
