import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt.Def
import sbt.ModuleID

object Dependencies {

  val laminar: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "com.raquo" %%% "laminar" % DependencyVersions.laminar
    )
  }

  val frontroute: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "io.frontroute" %%% "frontroute" % DependencyVersions.frontroute
    )
  }

  val scribe: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "com.outr" %%% "scribe" % DependencyVersions.scribe
    )
  }

}
