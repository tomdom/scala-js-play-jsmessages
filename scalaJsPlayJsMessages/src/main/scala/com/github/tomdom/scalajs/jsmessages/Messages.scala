package com.github.tomdom.scalajs.jsmessages

import scala.scalajs.js

trait MessagesStatic extends js.Object {
  def apply(key: String, params: js.Any*): String = js.native
  def apply(keys: js.Array[String], params: js.Any*): String = js.native

  var messages: js.Dictionary[String] = js.native
}
