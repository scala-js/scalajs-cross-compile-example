name := "Foo root project"

lazy val root = project.in(file(".")).aggregate(fooJS, fooJVM).settings(
  publish := {},
  publishLocal := {}
)

lazy val fooSharedSettings = Seq(
    name := "foo",
    version := "0.1-SNAPSHOT",
    unmanagedSourceDirectories in Compile +=
      baseDirectory.value / ".." / "foo-shared" / "src" / "main" / "scala"
)

lazy val fooJS = project.in(file("foo-js"))
  .settings(scalaJSSettings: _*)
  .settings(fooSharedSettings: _*)
  .settings(
    // Add JS-specific settings here
  )

lazy val fooJVM = project.in(file("foo-jvm"))
  .settings(fooSharedSettings: _*)
  .settings(
    // Add JVM-specific settings here
  )
