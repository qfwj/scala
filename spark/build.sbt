name := "spark"

version := "0.1"

scalaVersion := "2.11.12"


libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.3"

libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.2.3" % "provided"

