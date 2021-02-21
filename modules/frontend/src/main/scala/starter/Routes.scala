package starter

import com.raquo.laminar.api.L._
import io.frontroute._
import org.scalajs.dom.document
import starter.components.PageLayout
import starter.pages._

import scala.concurrent.duration._
import scala.scalajs.js.timers.setInterval

object Routes {

  private var counter = 0
  private val someVar: Var[String] = Var("this string is the initial value of the var")

  private val (routeResult, route) = makeRoute[Element] { render =>
    concat(
      (pathEnd | path("index")) {
        render { IndexPage() }
      },
      pathPrefix("pages") {
        concat(
          path("page-1") {
            signal(someVar.signal) { something =>
              render { Page1(something) }
            }
          },
          path("page-2") {
            render { Page2() }
          }
        )
      },
      pathPrefix("page-with-tabs") {
        (path(segment) | pathEnd.mapTo("tab-1")).signal { $tab =>
          render { PageWithTabs($tab) }
        }
      },
      path("page-with-params") {
        (maybeParam("param-1").signal & maybeParam("param-2").signal) { (param1, param2) =>
          render { PageWithParams(param1, param2) }
        }
      },
      render { PageNotFound() }
    )
  }

  private val currentRender = routeResult.map(_.getOrElse(div("initializing...")))

  def start(): Unit = {
    val container = document.getElementById("app-container")
    runRoute(route, LocationProvider.browser(windowEvents.onPopState))(unsafeWindowOwner)
    BrowserNavigation.emitPopStateEvent()
    val _ = render(container, PageLayout(currentRender))
    val _ = setInterval(3.seconds) {
      counter = counter + 1
      someVar.writer.onNext(s"this is an updated value inside the var: $counter")
    }
  }
}
