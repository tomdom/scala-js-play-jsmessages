package com.github.tomdom.scalajs.jsmessages.all

import com.github.tomdom.scalajs.jsmessages.MessagesStatic

import scala.scalajs.js

trait AllMessagesStatic extends js.Object {
  def apply(lang: String): MessagesStatic = js.native
  def apply(lang: String, key: String, params: js.Any*): String = js.native
  def apply(lang: String, keys: js.Array[String], params: js.Any*): String = js.native

  var messages: js.Dictionary[js.Dictionary[String]] = js.native
}
