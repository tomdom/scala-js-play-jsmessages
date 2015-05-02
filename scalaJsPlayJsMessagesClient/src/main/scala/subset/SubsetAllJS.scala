package subset

import com.github.tomdom.scalajs.jsmessages.all._
import org.scalajs.dom._

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSExport

@JSExport
object SubsetAllJS {
  @JSExport
  def script(): Unit = {
    val tests = ListBuffer[String]()

    tests += Messages("en-US", "greeting", "World", "Ignore this")
    tests += Messages("en-US", Array("wrong", "try again", "greeting"), "People")
    tests += Messages("en-US", "root.parent.child")
    tests += Messages("en-US", "apostrophe")
    tests += Messages("en-US", "error.maxLength")

    for (t <- tests) {
      val el = document.createElement("div")
      el.innerHTML = t
      document.body.appendChild(el)
    }
  }
}
