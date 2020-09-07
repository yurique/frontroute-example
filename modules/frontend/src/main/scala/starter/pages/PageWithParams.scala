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
        cls := "shadow appearance-none border rounded flex-1 py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline",
        value <-- $param1.map(_.getOrElse("")),
        placeholder := "set param-1",
        inContext { el =>
          onChange --> { _ =>
          }
        }
      )
    val input2 =
      input(
        cls := "shadow appearance-none border rounded flex-1 py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline",
        value <-- $param2.map(_.getOrElse("")),
        placeholder := "set param-2",
        inContext { el =>
          onChange --> { _ =>
            BrowserTools.setParam("param-2", el.ref.value)
          }
        }
      )
    div(
      cls := "space-y-10 w-full",
      div(
        cls := "text-3xl font-bold text-purple-900",
        "I'm a page with params"
      ),
      div(
        cls := "flex space-x-4 w-1/2",
        input1,
        button(
          cls := "inline-flex justify-center border border-transparent rounded-md focus:outline-none transition ease-in-out duration-150 px-4 py-2 text-sm text-lg text-indigo-900 bg-indigo-200 hover:bg-indigo-300 focus:border-blue-300 focus:shadow-outline-blue active:bg-indigo-400",
          "set param-1 value",
          onClick --> { _ =>
            BrowserTools.setParam("param-1", input1.ref.value)
          }
        )
      ),
      div(
        cls := "flex space-x-4 w-1/2",
        input2,
        button(
          cls := "inline-flex justify-center border border-transparent rounded-md focus:outline-none transition ease-in-out duration-150 px-4 py-2 text-sm text-lg text-indigo-900 bg-indigo-200 hover:bg-indigo-300 focus:border-blue-300 focus:shadow-outline-blue active:bg-indigo-400",
          "set param-2 value",
          onClick --> { _ =>
            BrowserTools.setParam("param-2", input2.ref.value)
          }
        )
      ),
      div(
        div(
          cls := "flex space-x-2",
          span("param-1 value:"),
          span(child.text <-- $param1.map(_.getOrElse("NOT SET")))
        ),
        div(
          cls := "flex space-x-2",
          span("param-2 value:"),
          span(child.text <-- $param2.map(_.getOrElse("NOT SET")))
        )
      ),
      div(
        cls := "w-full",
        textArea(
          cls := "w-full bg-purple-800 text-purple-100 p-4 text-xl",
          rows := 20,
          "You can edit me, and I'm staying here even if you change the parameters (and the URL changes)"
        )
      )      
    )
  }

}
