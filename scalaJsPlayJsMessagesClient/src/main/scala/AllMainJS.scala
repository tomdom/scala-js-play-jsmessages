import com.github.tomdom.scalajs.jsmessages.all._
import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSExport

@JSExport
object AllMainJS {
  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    tests += Messages("en", "greeting", "World", "Ignore this") + Messages("en", "empty")
    tests += Messages("en", Array("wrong", "try again", "greeting"), "People")
    tests += Messages("en", "root.parent.child")
    tests += Messages("en", "multiple.arguments", "Zero", 1)
    tests += Messages("en", "wrong.key")
    tests += Messages("en", "apostrophe")

    // Updating some messages
    val ms: scala.collection.mutable.Map[String, js.Dictionary[String]] = Messages.messages
    val enMs: scala.collection.mutable.Map[String, String] = ms("en")
    enMs("greeting") = "Hi there {0}!"
    enMs("wrong.key") = "Now valid"

    tests += Messages("en", "greeting", "World", "Ignore this")
    tests += Messages("en", "wrong.key")

    // Updating all messages
    Messages.messages = Map(
      "en" -> Map(
        "greeting" -> "Greetings baby!",
        "multiple.arguments" -> "{0}, {1} and {2}"
      ).toJSDictionary,
      "fr" -> Map(
        "greeting" -> "Salut toi!",
        "multiple.arguments" -> "{0}, {1} et {2}"
      ).toJSDictionary
    ).toJSDictionary

    tests += Messages("en", "greeting", "No World", "Ignore this")
    tests += Messages("en", "multiple.arguments", "Zero", 1111, true)

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}
