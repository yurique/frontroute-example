package starter.components

import com.raquo.laminar.api.L._

object PageChrome {

  private def navLink(where: String, mods: Mod[HtmlElement]*) =
    Link(
      where,
      cls := "hover:text-white hover:bg-gray-700 focus:outline-none focus:text-white focus:bg-gray-700",
      mods
    )

  def apply($child: Signal[Element]): HtmlElement =
    div(
      cls := "container mx-auto",
      div(
        cls := "px-2 pt-2 pb-3 bg-gray-800 flex items-baseline",
        navLink(
          "/index",
          cls := "text-gray-300",
          "Index Page"
        ),
        navLink(
          "/pages/page-1",
          cls := "text-gray-300",
          "Page 1"
        ),
        navLink(
          "/pages/page-2",
          cls := "text-gray-300",
          "Page 2"
        ),
        navLink(
          "/page-with-tabs",
          cls := "text-gray-300",
          "Page with Tabs"
        ),
        navLink(
          "/page-with-params",
          cls := "text-gray-300",
          "Page with Params"
        )
      ),
      div(
        cls := "bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10",
        child <-- $child
      )
    )

}
