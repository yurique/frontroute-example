package starter.config

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Configuration {

  @js.native
  @JSImport("frontend-config", JSImport.Namespace)
  private object ConfigGlobalScope extends js.Object {

    val config: js.Object = js.native

  }

  lazy val config: js.Object = ConfigGlobalScope.config

}
