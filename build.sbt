name := "Foo root project"

version := "0.1"

lazy val root = project.in(file(".")).aggregate()

lazy val fooJS = project.in(file("foo-js")).settings(scalaJSSettings: _*).settings(
  name := "Foo Js Build",
  unmanagedSourceDirectories in Compile += root.base / "foo-shared" / "src" / "main" / "scala"
)

lazy val fooJVM = project.in(file("foo-jvm")).settings(
  name := "Foo Jvm Build",
  unmanagedSourceDirectories in Compile += root.base / "foo-shared" / "src" / "main" / "scala"
)
