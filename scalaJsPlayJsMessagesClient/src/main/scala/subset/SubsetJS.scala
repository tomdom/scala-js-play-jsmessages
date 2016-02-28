package subset

import com.github.tomdom.scalajs.jsmessages._
import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSExport

@JSExport
object SubsetJS {
  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    tests += Messages("greeting", "World", "Ignore this")
    tests += Messages(Array("wrong", "try again", "greeting"), "People")
    tests += Messages("root.parent.child")
    tests += Messages("apostrophe")
    tests += Messages("error.maxLength")

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}
