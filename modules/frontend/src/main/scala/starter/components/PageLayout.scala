package starter.components

import com.raquo.laminar.api.L._

object PageLayout {

  private def navLink(where: String, mods: Mod[HtmlElement]*) =
    a(
      href := where,
      cls := "hover:text-white hover:bg-gray-700 focus:outline-none focus:text-white focus:bg-gray-700",
      mods
    )

  private def menu() = div(
      cls := "px-4 py-4  bg-gray-800 flex items-baseline space-x-8",
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
    )

  def apply(mods: Mod[HtmlElement]*): HtmlElement = div(
    cls := "container mx-auto mt-4",
    menu(),
    div(
      cls := "bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10",
      mods
    )
  )
}
