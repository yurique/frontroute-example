package starter.pages

import com.raquo.laminar.api.L._

object Page1 {

  def apply(something: String): HtmlElement =
    div(
      div(
        cls := "text-3xl font-bold text-purple-900",
        "I'm Page 1"
      ),
      div(
        s"Here's something: $something"
      ),
      div(
        "Note that this page re-renders everytime `something` changes."
      )
    )

}
