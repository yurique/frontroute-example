import sbtcrossproject.CrossPlugin.autoImport.crossProject
import sbtcrossproject.CrossPlugin.autoImport.CrossType

organization in ThisBuild := "io.frontroute"

name := "frontroute-example"

version in ThisBuild := "0.12.0-SNAPSHOT"

scalaVersion in ThisBuild := Settings.versions.scala

lazy val frontend =
  (crossProject(JSPlatform).crossType(CrossType.Pure) in file("modules/frontend"))
    .jsSettings(
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
      scalaJSLinkerConfig ~= { _.withESFeatures(_.withUseECMAScript2015(false)) },
      scalaJSUseMainModuleInitializer := true,
      libraryDependencies ++= Settings.dependencies.value
    )

lazy val root = project.in(file(".")).aggregate(frontend.js)
