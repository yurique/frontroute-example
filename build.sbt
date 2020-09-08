import sbtcrossproject.CrossPlugin.autoImport.crossProject
import sbtcrossproject.CrossPlugin.autoImport.CrossType

organization in ThisBuild := "app.tulz"

name := "laminar-router-example"

version in ThisBuild := "0.10.1"

scalaVersion in ThisBuild := Settings.versions.scala

scalacOptions in ThisBuild ++= Settings.scalacOptions

addCompilerPlugin(
  "org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full
)

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")

lazy val frontend =
  (crossProject(JSPlatform).crossType(CrossType.Pure) in file("modules/frontend"))
    .disablePlugins(RevolverPlugin)
    .jsSettings(
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
      libraryDependencies ++= Settings.dependencies.value
    )

lazy val root = project.in(file(".")).aggregate(frontend.js)
