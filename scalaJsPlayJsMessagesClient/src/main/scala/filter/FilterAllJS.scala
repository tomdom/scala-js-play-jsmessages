package filter

import com.github.tomdom.scalajs.jsmessages.all._
import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSExport

@JSExport
object FilterAllJS {
  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    var MessagesEn = Messages("en")

    tests += MessagesEn("error.required")
    tests += MessagesEn("greeting", "World", "Ignore this")
    tests += MessagesEn(Array("wrong", "try again", "greeting"), "People")
    tests += MessagesEn("root.parent.child")

    tests += Messages("en", "error.maxLength")

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}