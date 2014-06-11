import scala.scalajs.js

object Main extends js.JSApp {

  def main() = {
    val lib = new MyLibrary
    println(lib.sq(2))
  }

}
