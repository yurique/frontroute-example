package starter.pages

import com.raquo.laminar.api.L._
import starter.components._

object PageNotFound {

  def apply(): HtmlElement =
    div(
      cls := "text-3xl font-bold text-purple-900",
      "Opps, Not Found"
    )

}
