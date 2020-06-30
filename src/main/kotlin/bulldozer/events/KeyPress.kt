package bulldozer.events

import bulldozer.Event

class KeyPress(k:Int) : Event() {
    val key = k
}