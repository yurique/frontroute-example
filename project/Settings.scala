import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt._

object Settings {

  object versions {
    val scala = "2.13.3"
    val laminar = "0.10.2"
    val `laminar-router` = "0.10.0"
    val scribe = "2.7.12"
  }

  val scalacOptions = Seq(
    "-target:jvm-1.8",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-Xlint:nullary-unit,inaccessible,infer-any,missing-interpolator,private-shadow,type-parameter-shadow,poly-implicit-overload,option-implicit,delayedinit-select,stars-align",
    "-Xcheckinit",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-Ywarn-value-discard",
    // for Scala 2.13 only
    "-Ymacro-annotations",
    // ---
    "-encoding",
    "utf8"
  )

  object libs {

    val laminar: Def.Initialize[Seq[ModuleID]] = Def.setting {
      Seq(
        "com.raquo" %%% "laminar" % versions.laminar
      )
    }

    val `laminar-router`: Def.Initialize[Seq[ModuleID]] = Def.setting {
      Seq(
        "app.tulz" %%% "laminar-router" % versions.`laminar-router`
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
      libs.`laminar-router`.value
    )
  }

}
