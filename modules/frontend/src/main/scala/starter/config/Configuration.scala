package starter.config

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

object Configuration {

  @js.native
  @JSGlobal("window")
  private[this] object ConfigGlobalScope extends js.Object {

    val config: js.Object = js.native

  }

  lazy val config: js.Object = ConfigGlobalScope.config

}
