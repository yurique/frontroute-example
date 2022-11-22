inThisBuild(
  List(
    organization := "io.frontroute",
    version := "0.13.1",
    scalaVersion := DependencyVersions.scala
  )
)

lazy val frontend =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/frontend"))
    .jsSettings(
      name := "frontend",
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
      scalaJSLinkerConfig ~= { _.withESFeatures(_.withESVersion(org.scalajs.linker.interface.ESVersion.ES2015)) },
      scalaJSUseMainModuleInitializer := true,
      libraryDependencies ++= Seq.concat(
        Dependencies.scribe.value,
        Dependencies.laminar.value,
        Dependencies.frontroute.value
      ),
      ScalaOptions.fixOptions
    )

lazy val root = project
  .in(file("."))
  .settings(
    name := "frontroute-example"
  )
  .aggregate(frontend.js)
