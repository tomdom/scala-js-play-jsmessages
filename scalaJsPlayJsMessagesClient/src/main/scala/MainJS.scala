import com.github.tomdom.scalajs.jsmessages._

import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSExport

@JSExport
object MainJS extends js.JSApp {
  def main(): Unit = {}

  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    tests += Messages("greeting", "World", "Ignore this") + Messages("empty")
    tests += Messages(Array("wrong", "try again", "greeting"), "People")
    tests += Messages("root.parent.child")
    tests += Messages("multiple.arguments", "Zero", 1)
    tests += Messages("wrong.key")
    tests += Messages("apostrophe")

    // Updating some messages
    val ms: scala.collection.mutable.Map[String, String] = Messages.messages
    ms("greeting") = "Hi there {0} US!"
    ms("wrong.key") = "Now valid US"

    tests += Messages("greeting", "World", "Ignore this")
    tests += Messages("wrong.key")

    // Updating all messages
    Messages.messages = scala.collection.mutable.Map(
      "greeting" -> "Greetings baby US!",
      "multiple.arguments" -> "{0}, {1} and {2}"
    ).toJSDictionary

    tests += Messages("greeting", "No World", "Ignore this")
    tests += Messages("multiple.arguments", "Zero", 1111, true)

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}
