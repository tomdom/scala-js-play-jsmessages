package com.github.tomdom.scalajs.jsmessages

import scala.scalajs.js

@js.native
trait AllMessagesStatic extends js.Object {
  def apply(lang: String): MessagesStatic = js.native
  def apply(lang: String, key: String, params: js.Any*): String = js.native
  def apply(lang: String, keys: js.Array[String], params: js.Any*): String = js.native

  var messages: js.Dictionary[js.Dictionary[String]] = js.native
}

@js.native
object AllMessages extends js.GlobalScope {
  val Messages: AllMessagesStatic = js.native
}