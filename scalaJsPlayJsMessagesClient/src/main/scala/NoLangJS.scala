import com.github.tomdom.scalajs.jsmessages.all._
import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSExport

@JSExport
object NoLangJS {
  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    val MessagesUndefined = Messages(null.asInstanceOf[String])

    tests += MessagesUndefined("greeting", "World", "Ignore this") + MessagesUndefined("empty")
    tests += MessagesUndefined(Array("wrong", "try again", "greeting"), "People")
    tests += MessagesUndefined("root.parent.child")
    tests += MessagesUndefined("multiple.arguments", "Zero", 1)
    tests += MessagesUndefined("wrong.key")
    tests += MessagesUndefined("apostrophe")

    // Updating some messages
    val ms: scala.collection.mutable.Map[String, js.Dictionary[String]] = Messages.messages
    val defaultMs: scala.collection.mutable.Map[String, String] = ms("default")
    defaultMs("greeting") = "Hi there {0}!"
    defaultMs("wrong.key") = "Now valid"

    tests += MessagesUndefined("greeting", "World", "Ignore this")
    tests += MessagesUndefined("wrong.key")

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

    tests += MessagesUndefined("greeting", "No World", "Ignore this")
    tests += MessagesUndefined("multiple.arguments", "Zero", 1111, true)

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}
