import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt._

object Settings {

  object versions {
    val scala = "2.13.4"
    val laminar = "0.12.0-RC1"
    val frontroute = "0.12.0-RC1"
    val scribe = "3.3.3"
  }

  object libs {

    val laminar: Def.Initialize[Seq[ModuleID]] = Def.setting {
      Seq(
        "com.raquo" %%% "laminar" % versions.laminar
      )
    }

    val frontroute: Def.Initialize[Seq[ModuleID]] = Def.setting {
      Seq(
        "io.frontroute" %%% "frontroute" % versions.frontroute
      )
    }

    val scribe: Def.Initialize[Seq[ModuleID]] = Def.setting {
      Seq(
        "com.outr" %%% "scribe" % versions.scribe
      )
    }

  }

  val dependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq.concat(
      libs.scribe.value,
      libs.laminar.value,
      libs.frontroute.value
    )
  }

}
