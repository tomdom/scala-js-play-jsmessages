package filter

import com.github.tomdom.scalajs.jsmessages.Messages._
import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSExport

@JSExport
object FilterJS {
  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    tests += Messages("error.required")
    tests += Messages("greeting", "World", "Ignore this")
    tests += Messages(Array("wrong", "try again", "greeting"), "People")
    tests += Messages("root.parent.child")
    tests += Messages("error.maxLength")

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}
