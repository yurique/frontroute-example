import sbtcrossproject.CrossPlugin.autoImport.crossProject
import sbtcrossproject.CrossPlugin.autoImport.CrossType

ThisBuild / organization := "io.frontroute"

ThisBuild / version in ThisBuild := "0.12.0-SNAPSHOT"

ThisBuild / scalaVersion := Settings.versions.scala

lazy val frontend =
  (crossProject(JSPlatform).crossType(CrossType.Pure) in file("modules/frontend"))
    .jsSettings(
      name := "frontend",
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
      scalaJSLinkerConfig ~= { _.withESFeatures(_.withUseECMAScript2015(false)) },
      scalaJSUseMainModuleInitializer := true,
      libraryDependencies ++= Settings.dependencies.value,
      scalacOptions ~= (_.filterNot(
        Set("-Wdead-code")
      ))
    )

lazy val root = project
  .in(file("."))
  .settings(
    name := "frontroute-example"
  )
  .aggregate(frontend.js)
