package starter

import com.raquo.laminar.api.L._
import io.frontroute._
import starter.pages._

import scala.concurrent.duration._
import scala.scalajs.js.timers.setInterval

object Routes {

  private var counter = 0
  private val someVar: Var[String] = Var("this string is the initial value of the var")

  private val route = firstMatch(
    (pathEnd | path("index")) { IndexPage.apply() },
    pathPrefix("pages") { firstMatch(
        path("page-1") { signal(someVar.signal) { something => Page1(something) }},
        (path("page-2") & historyState) { state => Page2(state) }
    )},
    pathPrefix("page-with-tabs") {
      (path(segment) | pathEnd.mapTo("tab-1")).signal { $tab => PageWithTabs($tab)}
    },
    path("page-with-params") { (maybeParam("param-1").signal & maybeParam("param-2").signal) {
        (param1, param2) => PageWithParams(param1, param2)
    }},
    (noneMatched & extractUnmatchedPath) { unmatched => PageNotFound(unmatched) },
  )

  def apply() = {
    val _ = setInterval(3.seconds) {
      counter = counter + 1
      someVar.writer.onNext(s"this is an updated value inside the var: $counter")
    }
    route
  }
}
