import com.github.tomdom.scalajs.jsmessages.all._
import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSExport

@JSExport
object FrJS {
  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    val MessagesFr = Messages("fr")

    tests += MessagesFr("greeting", "World", "Ignore this") + MessagesFr("empty")
    tests += MessagesFr(Array("wrong", "try again", "greeting"), "People")
    tests += MessagesFr("root.parent.child")
    tests += MessagesFr("multiple.arguments", "Zero", 1)
    tests += MessagesFr("wrong.key")
    tests += MessagesFr("apostrophe")

    // Updating some messages
    val ms: scala.collection.mutable.Map[String, js.Dictionary[String]] = Messages.messages
    val frMs: scala.collection.mutable.Map[String, String] = ms("fr")
    frMs("greeting") = "Salut ici {0}!"
    frMs("wrong.key") = "Désormais valide"

    tests += MessagesFr("greeting", "World", "Ignore this")
    tests += MessagesFr("wrong.key")

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

    tests += MessagesFr("greeting", "No World", "Ignore this")
    tests += MessagesFr("multiple.arguments", "Zero", 1111, true)

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}
