# scala-js-play-jsmessages
Scala.js bindings for [play-jsmessages](http://github.com/julienrf/play-jsmessages)

To use it you need to add the following line to your build.sbt

<pre>resolvers += ("tomdom-mvn snapshots" at "https://github.com/tomdom/tomdom-mvn/raw/master/snapshots")</pre>

and add this to your library dependencies

<pre>"com.github.tomdom" %% "scala-js-play-jsmessages" % "0.2-SNAPSHOT"</pre>

Like play framework 2.5.0 scala-js-play-jsmessages no longer supports scala 2.10.

To test is run:

<pre>sbt</pre>

then

<pre>project scalaJsPlayJsMessagesServer</pre>

and

<pre>run</pre>
