package org.trofimov.server.helpers

import java.net.URLEncoder
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus

/**
 * Created by ivan on 10.05.17.
 */

data class Foo(val name: String, val content: String, val isString: Boolean)

fun toJSON(vararg args: Foo): String {
    var json = ""
    json += "{"
    for (arg in args) {
        val key = arg.name//URLEncoder.encode(arg.name, "UTF-8")
        val v = arg.content
        if (arg.isString) {
            json += """"$key":"$v", """
        } else {
            json += """"$key":$v, """
        }
    }
    json = json.substring(0, json.length - 2)
    json += "}"
    return json
}

fun toJSONArray(args: List<String>): String {
    var json = ""
    json += "["
    for (arg in args) {
        if (arg[0] == '{') {
            json += """$arg, """
        } else {
            json += """"$arg", """
        }
    }
    if (args.isNotEmpty()) {
        json = json.substring(0, json.length - 2)
    }
    json += "]"
    return json
}