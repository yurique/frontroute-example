package starter.components

import com.raquo.laminar.api.L._
import starter.BrowserTools

object Link {

  def apply(
    where: String,
    mods: Modifier[HtmlElement]*
  ): HtmlElement = {
    a(
      href := where,
      cls := "mt-1 block px-3 py-2 rounded-md text-base font-medium",
      onClick.preventDefault --> { _ =>
        BrowserTools.pushState(null, null, where)
      },
      mods
    )
  }

}
