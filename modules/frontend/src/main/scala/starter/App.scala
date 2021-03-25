package starter

import com.raquo.laminar.api.L._
import io.frontroute._

object App {

  def main(args: Array[String]): Unit = {
    val _ = documentEvents.onDomContentLoaded.foreach { _ =>
      LinkHandler.install()
      Routes.start()
    }(unsafeWindowOwner)
  }
}
