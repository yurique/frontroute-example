package starter.pages

import com.raquo.laminar.api.L._
import com.raquo.airstream.core.Signal

object Page1 {

  def apply(something: Signal[String]): HtmlElement =
    div(
      cls := "space-y-4",
      div(
        cls := "text-3xl font-bold text-indigo-900",
        "I'm Page 1"
      ),
      div(
        cls := "flex space-x-4",
        div(
          s"Here's `something`:"
        ),
        div(
          cls := "font-medium text-blue-800",
          value <-- something,
        )
      ),
      div(
        "Note that this page re-renders everytime `something` changes."
      )
    )

}
