name := "Foo root project"

lazy val root = project.in(file("."))
  .aggregate(fooJS, fooJVM)
  .settings(
    publish := {},
    publishLocal := {}
  )

// Project containing source code shared between the JS and JVM projects.
// This project should never be compiled or packages but is simply an IntelliJ IDEA
// friendly alternative to a shared code directory. Projects depending on this
// projects source code should declare a dependency as 'Provided' AND append
// this projects source directory manually to 'unmanagedSourceDirectories'.
lazy val fooShared = project.in(file("foo-shared"))

lazy val fooSharedSettings = Seq(
  name := "foo",
  version := "0.1-SNAPSHOT",
  // NOTE: The following line will generate a warning in IntelliJ IDEA, which can be ignored:
  // "The following source roots are outside the corresponding base directories"
  unmanagedSourceDirectories in Compile += (scalaSource in (fooShared, Compile)).value
)

lazy val fooJS = project.in(file("foo-js"))
  .dependsOn(fooShared % Provided)
  .settings(scalaJSSettings: _*)
  .settings(fooSharedSettings: _*)
  .settings(
    // Add JS-specific settings here
  )

lazy val fooJVM = project.in(file("foo-jvm"))
  .dependsOn(fooShared % Provided)
  .settings(fooSharedSettings: _*)
  .settings(
    // Add JVM-specific settings here
  )
