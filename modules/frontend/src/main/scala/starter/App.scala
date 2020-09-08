package starter

import com.raquo.laminar.api.L._
import org.scalajs.dom
import org.scalajs.dom.document
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.annotation.JSExportTopLevel
import starter.components.PageChrome
import starter.pages._
import app.tulz.routing._
import app.tulz.routing.directives._
import scala.scalajs.js.timers._
import scala.concurrent.duration._

@JSExportTopLevel("App")
object App {

  private var counter = 0
  private val someVar: Var[String] = Var("this string is the initial value of the var")

  private val currentRender = Var[Element](
    div("initializing...")
  )

  private def completeRender(r: => Element): Route =
    complete {
      currentRender.writer.onNext(r)
      dom.window.scrollTo(0, 0)
    }

  @JSExport
  def start(): Unit = {

    val route =
      concat(
        (pathEnd | path("index")) {
          completeRender {
            IndexPage()
          }
        },
        pathPrefix("pages") {
          concat(
            path("page-1") {
              signal(someVar.signal) { something =>
                completeRender {
                  Page1(something)
                }
              }
            },
            path("page-2") {
              completeRender {
                Page2()
              }
            }
          )
        },
        pathPrefix("page-with-tabs") {
          (path(segment) | pathEnd.tmap(_ => Tuple1("tab-1"))).signal { $tab =>
            completeRender {
              PageWithTabs($tab)
            }
          }
        },
        path("page-with-params") {
          (maybeParam("param-1").signal & maybeParam("param-2").signal) { (param1, param2) =>
            completeRender {
              PageWithParams(param1, param2)
            }
          }
        },
        completeRender {
          PageNotFound()
        }
      )

    val container = document.getElementById("app-container")
    val _ =
      render(
        container,
        PageChrome(currentRender.signal)
      )

    runRoute(route, new BrowserRouteLocationProvider(windowEvents.onPopState)).foreach(_())(unsafeWindowOwner)
    BrowserNavigation.emitPopStateEvent()
    val _ = setInterval(3.seconds) {
      counter = counter + 1
      someVar.writer.onNext(s"this is an updated value inside the var: $counter")
    }
  }
}
