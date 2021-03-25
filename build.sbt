inThisBuild(
  List(
    organization := "io.frontroute",
    version := "0.12.2",
    scalaVersion := DependencyVersions.scala,
    resolvers += Resolver.sonatypeRepo("public")
  )
)

lazy val frontend =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/frontend"))
    .jsSettings(
      name := "frontend",
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
      scalaJSLinkerConfig ~= { _.withESFeatures(_.withUseECMAScript2015(false)) },
      scalaJSUseMainModuleInitializer := true,
      libraryDependencies ++= Seq.concat(
        Dependencies.scribe.value,
        Dependencies.laminar.value,
        Dependencies.frontroute.value
      ),
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
