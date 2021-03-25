package starter.pages

import com.raquo.laminar.api.L._
import io.frontroute.BrowserNavigation

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.util.Failure
import scala.util.Success
import scala.util.Try

object Page2 {

  def apply(state: Option[js.Any]): HtmlElement = {
    val input1 =
      input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "set state (something that JSON.parse can understand)",
      )
    div(
      cls := "space-y-10 w-full",
      div(
        cls := "text-3xl font-bold text-indigo-900",
        "I'm Page 2",
      ),
      div(
        cls := "flex items-center space-x-4",
        div(
          cls := "flex-1",
          input1
        ),
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "update state",
            onClick --> { _ =>
              Try(JSON.parse(input1.ref.value)) match {
                case Success(parsed) =>
                  BrowserNavigation.pushState(data = parsed)
                case Failure(exception) =>
                  BrowserNavigation.pushState(data = js.Dynamic.literal(error = s"failed to parse the string: ${exception.getMessage}"))
              }
            }
          )
        )
      ),
      div(
        span("state"),
        div(
          JSON.stringify(state.getOrElse("NO-STATE"))
        )
      )
    )
  }

}
