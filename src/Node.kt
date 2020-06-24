import processing.core.PApplet
import processing.core.PVector
import java.util.*
import com.google.gson.Gson

class Node () {
    var pos = PVector(0F, 0F)
    var parent = PVector(-1F, -1F)
    var f = 0F
    var g = 0F
    var h = 0F
    internal constructor(position : PVector) : this() {
        this.pos = position
    }
}

fun clone(t : Any): Any {
    var gson = Gson()
    var json = gson.toJson(t)
    return gson.fromJson(json, t.javaClass) as Any
}