package starter.pages

import com.raquo.laminar.api.L._

object PageNotFound {

  def apply(path: List[String]): HtmlElement =
    div(
      cls := "text-3xl font-bold text-indigo-900",
      s"""Oops, Not Found /${path.mkString("/")}"""
    )

}
