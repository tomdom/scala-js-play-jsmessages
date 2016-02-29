import com.github.tomdom.scalajs.jsmessages.AllMessages
import AllMessages._
import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSExport

@JSExport
object EnUsJS {
  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    val MessagesEn = Messages("en-US")

    tests += MessagesEn("greeting", "World", "Ignore this") + MessagesEn("empty")
    tests += MessagesEn(Array("wrong", "try again", "greeting"), "People")
    tests += MessagesEn("root.parent.child")
    tests += MessagesEn("multiple.arguments", "Zero", 1)
    tests += MessagesEn("wrong.key")
    tests += MessagesEn("apostrophe")

    // Updating some messages
    val ms: scala.collection.mutable.Map[String, js.Dictionary[String]] = Messages.messages
    val enMs: scala.collection.mutable.Map[String, String] = ms("en-US")
    enMs("greeting") = "Hi there {0} US!"
    enMs("wrong.key") = "Now valid US"

    tests += MessagesEn("greeting", "World", "Ignore this")
    tests += MessagesEn("wrong.key")

    // Updating all messages
    Messages.messages = Map(
      "en-US" -> Map(
        "greeting" -> "Greetings baby US!",
        "multiple.arguments" -> "{0}, {1} and {2}"
      ).toJSDictionary,
      "en" -> Map(
        "greeting" -> "Greetings baby!",
        "multiple.arguments" -> "{0}, {1} and {2}"
      ).toJSDictionary,
      "fr" -> Map(
        "greeting" -> "Salut toi!",
        "multiple.arguments" -> "{0}, {1} et {2}"
      ).toJSDictionary
    ).toJSDictionary

    tests += MessagesEn("greeting", "No World", "Ignore this")
    tests += MessagesEn("multiple.arguments", "Zero", 1111, true)

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}
