val commonSettings = Seq(
  scalaVersion := "2.11.6"
)

import sbt.Keys._
import sbt.Project.projectToRef

lazy val scalaJsPlayJsMessages = project
  .settings(commonSettings: _*)
  .settings(
    name := "scala-js-play-jsmessages",
    organization := "com.github.tomdom",
    version := "0.1-SNAPSHOT",
    crossScalaVersions := Seq("2.10.5", "2.11.6"),
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
      anorm,
      cache,
      ws,
      "com.vmunier" %% "play-scalajs-scripts" % "0.1.0",
      "org.webjars" % "jquery" % "1.11.1",
      "org.julienrf" %% "play-jsmessages" % "1.6.2"
    )
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
      "org.scala-js" %%% "scalajs-dom" % "0.8.0"
    )
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSPlay)
  .dependsOn(scalaJsPlayJsMessages)
