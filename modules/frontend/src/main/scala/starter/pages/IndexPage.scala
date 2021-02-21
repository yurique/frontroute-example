package starter.pages

import com.raquo.laminar.api.L._
import starter.config.Configuration

import scala.scalajs.js.JSON

object IndexPage {

  def apply(): HtmlElement =
    div(
      cls := "space-y-4",
      div(
        cls := "text-3xl font-bold text-indigo-900",
        "I'm the index page3"
      ),
      div(
        cls := "space-y-2",
        div(
          cls := "font-medium text-2xl",
          "Configuration:"
        ),
        pre(
          cls := "bg-indigo-900 text-indigo-100 p-4",
          code(
            JSON.stringify(Configuration.config, space = 4)
          )
        )
      )
    )

}
