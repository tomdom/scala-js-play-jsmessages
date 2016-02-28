val commonSettings = Seq(
  scalaVersion := "2.11.7"
)

import sbt.Keys._
import sbt.Project.projectToRef

lazy val scalaJsPlayJsMessages = project
  .settings(commonSettings: _*)
  .settings(
    name := "scala-js-play-jsmessages",
    organization := "com.github.tomdom",
    version := "0.1-SNAPSHOT",
    crossScalaVersions := Seq("2.10.6", "2.11.7"),
    publishTo := {
      val tomdomMvn = Path.userHome.absolutePath + "/projects/github/tomdom/tomdom-mvn"
      if (isSnapshot.value)
        Some(Resolver.file("file",  new File(tomdomMvn + "/snapshots")))
      else
        Some(Resolver.file("file",  new File(tomdomMvn + "/releases")))
    }
  )
  .enablePlugins(ScalaJSPlugin)

lazy val clients = Seq(scalaJsPlayJsMessagesClient)

lazy val scalaJsPlayJsMessagesServer = (project in file("scalaJsPlayJsMessagesServer"))
  .settings(commonSettings: _*)
  .settings(scalariformSettings: _*)
  .settings(
    scalaJSProjects := clients,
    pipelineStages := Seq(scalaJSProd, gzip),
    libraryDependencies ++= Seq(
      jdbc,
      cache,
      ws,
      specs2 % Test,
      "com.vmunier" %% "play-scalajs-scripts" % "0.4.0",
      "org.webjars" % "jquery" % "2.2.1",
      "org.julienrf" %% "play-jsmessages" % "2.0.0"
    ),
    resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
    // Play provides two styles of routers, one expects its actions to be injected, the
    // other, legacy style, accesses its actions statically.
    routesGenerator := InjectedRoutesGenerator
  )
  .enablePlugins(PlayScala)
  .aggregate(clients.map(projectToRef): _*)

lazy val scalaJsPlayJsMessagesClient = (project in file("scalaJsPlayJsMessagesClient"))
  .settings(commonSettings: _*)
  .settings(scalariformSettings: _*)
  .settings(
    persistLauncher := true,
    persistLauncher in Test := false,
    unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
    skip in packageJSDependencies := false,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0"
    )
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSPlay)
  .dependsOn(scalaJsPlayJsMessages)

// loads the jvm project at sbt startup
onLoad in Global := (Command.process("project scalaJsPlayJsMessagesServer", _: State)) compose (onLoad in Global).value