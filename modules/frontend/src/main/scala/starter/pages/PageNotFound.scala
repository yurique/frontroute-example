package starter.pages

import com.raquo.laminar.api.L._

object PageNotFound {

  def apply(): HtmlElement =
    div(
      cls := "text-3xl font-bold text-indigo-900",
      "Oops, Not Found"
    )

}
