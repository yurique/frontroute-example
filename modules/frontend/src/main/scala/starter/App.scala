package starter

import com.raquo.laminar.api.L._
import io.frontroute._
import starter.components.PageLayout
import org.scalajs.dom.document.querySelector

object App {
  def main(args: Array[String]): Unit = {
    render(
      querySelector("#app"),
      PageLayout(div(Routes())).amend(LinkHandler.bind)
    ).asInstanceOf[Unit]
  }
}
