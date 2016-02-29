import com.github.tomdom.scalajs.jsmessages.AllMessages
import AllMessages._
import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSExport

@JSExport
object CnJS {
  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    val MessagesCn = Messages("cn")

    tests += MessagesCn("greeting", "World", "Ignore this") + MessagesCn("empty")
    tests += MessagesCn(Array("wrong", "try again", "greeting"), "People")
    tests += MessagesCn("root.parent.child")
    tests += MessagesCn("multiple.arguments", "Zero", 1)
    tests += MessagesCn("wrong.key")
    tests += MessagesCn("apostrophe")

    // Updating some messages
    val ms: scala.collection.mutable.Map[String, js.Dictionary[String]] = Messages.messages
    val cnMs: scala.collection.mutable.Map[String, String] = ms("default")
    cnMs("greeting") = "Hi there {0}!"
    cnMs("wrong.key") = "Now valid"

    tests += MessagesCn("greeting", "World", "Ignore this")
    tests += MessagesCn("wrong.key")

    // Updating all messages
    Messages.messages = Map(
      "default" -> Map(
        "greeting" -> "Greetings baby!",
        "multiple.arguments" -> "{0}, {1} and {2}"
      ).toJSDictionary,
      "fr" -> Map(
        "greeting" -> "Salut toi!",
        "multiple.arguments" -> "{0}, {1} et {2}"
      ).toJSDictionary
    ).toJSDictionary

    tests += MessagesCn("greeting", "No World", "Ignore this")
    tests += MessagesCn("multiple.arguments", "Zero", 1111, true)

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}
