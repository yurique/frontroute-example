package starter.pages

import com.raquo.laminar.api.L._

object IndexPage {

  def apply(): HtmlElement =
    div(
      cls := "text-3xl font-bold text-purple-900",
      "I'm the index page"
    )

}
