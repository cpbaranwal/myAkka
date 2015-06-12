
//import AssemblyKeys._

name := "myAkka"

version := "1.0"

organization := "com.databricks"

scalaVersion := "2.10.4"


libraryDependencies ++= Seq(
"com.typesafe.akka" %% "akka-actor" % "2.3.11",
"org.apache.spark" % "spark-core_2.10" % "1.2.0" % "provided",
"net.sf.jopt-simple" % "jopt-simple" % "4.3",
"joda-time" % "joda-time" % "2.0"
)


updateOptions := updateOptions.value.withCachedResolution(true)


// Configure jar named used with the assembly plug-in
jarName in assembly := "my-project-assembly.jar"

// A special option to exclude Scala itself form our assembly jar, since Spark
// already bundles Scala.
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
