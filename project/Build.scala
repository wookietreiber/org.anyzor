import sbt._
import Keys._

import Dependencies._
import BuildSettings._

object BuildSettings {
  val buildOrganization = "org.anyzor"
  val buildVersion      = "0.1"
  val buildScalaVersion = "2.9.1"
  val akkaVersion       = "1.2"
  val latest            = "latest.integration"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization         := buildOrganization,
    version              := buildVersion,
    scalaVersion         := buildScalaVersion,
    libraryDependencies ++= Seq ( actor, specs2 )
  )
}

object OrganyzorBuild extends Build {
  lazy val root = Project ( "org-anyzor", file ( "." ),
    settings = buildSettings
  )

  lazy val gatherer = Project ( "gatherer", file ( "gatherer" ),
    settings = buildSettings
  )

  lazy val gathererFeed = Project ( "gatherer-feed", file ( "gatherer-feed" ),
    settings = buildSettings ++ Seq (
      libraryDependencies ++= Seq ( ssf4s )
    )
  ) dependsOn ( gatherer )
}

object Dependencies {

  // -----------------------------------------------------------------------
  // compile
  // -----------------------------------------------------------------------

  lazy val actor  = "se.scalablesolutions.akka"      % "akka-actor"   % akkaVersion
  lazy val remote = "se.scalablesolutions.akka"      % "akka-remote"  % akkaVersion

  lazy val scf4s  = "com.github.wookietreiber.scf4s" %% "scf4s"       % latest
  lazy val ssf4s  = "com.github.wookietreiber.ssf4s" %% "ssf4s"       % latest

  lazy val swing  = "org.scala-lang"                 %  "scala-swing" % buildScalaVersion

  // -----------------------------------------------------------------------
  // test
  // -----------------------------------------------------------------------

  lazy val specs2 = "org.specs2" %% "specs2" % "1.6.1" % "test"

}

object Resolvers {
  lazy val typesafe = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"
}
