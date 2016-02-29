package com.github.tomdom.scalajs.jsmessages

import scala.scalajs.js

@js.native
trait MessagesStatic extends js.Object {
  def apply(key: String, params: js.Any*): String = js.native
  def apply(keys: js.Array[String], params: js.Any*): String = js.native

  var messages: js.Dictionary[String] = js.native
}

@js.native
object Messages extends js.GlobalScope {
  val Messages: MessagesStatic = js.native
}