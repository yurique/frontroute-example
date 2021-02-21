package starter.pages

import com.raquo.laminar.api.L._

object PageWithTabs {

  private val tabs = Seq(
    "Tab 1" -> "tab-1",
    "Tab 2" -> "tab-2",
    "Tab 3" -> "tab-3"
  )

  def apply(
    $tab: Signal[String]
  ): HtmlElement =
    div(
      cls := "space-y-10 w-full",
      div(
        cls := "text-3xl font-bold text-indigo-900",
        "I'm a page with tabs"
      ),
      div(
        cls := "flex space-x-4",
        tabs.map { case (title, path) =>
          val $isCurrentTab = $tab.map(_ == path)
          a(
            href := s"/page-with-tabs/$path",
            cls := "hover:text-gray-800 bg-gray-100 hover:bg-gray-300 focus:outline-none focus:text-gray-800 focus:bg-gray-300",
            cls := "border-b-4 border-transparent",
            cls <-- $isCurrentTab.map { isCurrentTab =>
              Seq(
                "text-gray-900" -> isCurrentTab,
                "text-gray-700" -> !isCurrentTab,
                "border-indigo-300" -> isCurrentTab
              )
            },
            title
          )
        }
      ),
      div(
        tabs.map { case (title, path) =>
          val $isCurrentTab = $tab.map(_ == path)
          div(
            cls := "p-10 text-3xl text-indigo-900",
            cls <-- $isCurrentTab.map { isCurrentTab =>
              Seq(
                "hidden" -> !isCurrentTab
              )
            },
            s"I'm $title"
          )
        }
      ),
      div(
        cls := "w-full",
        textArea(
          cls := "w-full bg-indigo-900 text-indigo-100 p-4 text-xl",
          rows := 20,
          "You can edit me, and I'm staying here even if you change the tabs (and the URL changes)"
        )
      )
    )

}
