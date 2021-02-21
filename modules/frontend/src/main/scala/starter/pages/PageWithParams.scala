package starter.pages

import com.raquo.laminar.api.L._
import starter.BrowserTools

object PageWithParams {

  def apply(
    $param1: Signal[Option[String]],
    $param2: Signal[Option[String]]
  ): HtmlElement = {
    val input1 =
      input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        value <-- $param1.map(_.getOrElse("")),
        placeholder := "set param-1",
      )
    val input2 =
      input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        value <-- $param2.map(_.getOrElse("")),
        placeholder := "set param-2"
      )
    div(
      cls := "space-y-10 w-full",
      div(
        cls := "text-3xl font-bold text-indigo-900",
        "I'm a page with params"
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
            "update param-1 value",
            onClick --> { _ =>
              BrowserTools.setParam("param-1", input1.ref.value)
            }
          )
        )
      ),
      div(
        cls := "flex items-center space-x-4",
        div(
          cls := "flex-1",
          input2
        ),
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "update param-2 value",
            onClick --> { _ =>
              BrowserTools.setParam("param-2", input2.ref.value)
            }
          )
        )
      ),
      div(
        div(
          cls := "flex space-x-2",
          span("param-1 value:"),
          span(
            cls := "font-medium text-blue-800",
            child.text <-- $param1.map(_.getOrElse("NOT SET"))
          )
        ),
        div(
          cls := "flex space-x-2",
          span("param-2 value:"),
          span(
            cls := "font-medium text-blue-800",
            child.text <-- $param2.map(_.getOrElse("NOT SET"))
          )
        )
      ),
      div(
        cls := "w-full",
        textArea(
          cls := "w-full bg-indigo-900 text-indigo-100 p-4 text-xl",
          rows := 20,
          "You can edit me, and I'm staying here even if you change the parameters (and the URL changes)"
        )
      )
    )
  }

}
