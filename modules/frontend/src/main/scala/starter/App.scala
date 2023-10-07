package starter

import com.raquo.laminar.api.L._
import frontroute._
import starter.components.PageLayout
import org.scalajs.dom.document.querySelector
import starter.pages._

import scala.concurrent.duration._
import scala.scalajs.js.timers.SetIntervalHandle
import scala.scalajs.js.timers.setInterval
import scala.scalajs.js.timers.clearInterval

object App {

  private var counter = 0
  private val someVar: Var[String] = Var("initial value of the `someVar`")

  def main(args: Array[String]): Unit = {
    val _ = render(
      querySelector("#app"),
      routes(
        PageLayout(
          (pathEnd | path("index")) {
            IndexPage()
          },
          pathPrefix("pages") {
            firstMatch(
              (path("page-1")) { Page1(someVar.signal) },
              (path("page-2") & historyState) { state => Page2(state) }
            )
          },
          pathPrefix("page-with-tabs") {
            (path(segment) | pathEnd.mapTo("tab-1")).signal { $tab => PageWithTabs($tab) }
          },
          path("page-with-params") {
            (maybeParam("param-1").signal & maybeParam("param-2").signal) { (param1, param2) =>
              PageWithParams(param1, param2)
            }
          },
          (noneMatched & extractUnmatchedPath) { unmatched => PageNotFound(unmatched) }
        )
      ).amend(LinkHandler.bind, startTimer)
    )
  }

  private def startTimer: Mod[Element] =
    onMountUnmountCallbackWithState[Element, SetIntervalHandle](
      { _ =>
        setInterval(3.seconds) {
          counter = counter + 1
          someVar.writer.onNext(s"updated value inside `someVar`: $counter")
        }
      },
      { (_, handle) => handle.foreach(clearInterval) }
    )
}
