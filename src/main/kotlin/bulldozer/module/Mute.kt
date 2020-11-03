package bulldozer.module

import bulldozer.Module

class Mute: Module("Mute", emptyArray(), false){
    public var temporaryException: Boolean = false
    //ClientConnection.java
    
    override fun onEnable() {
        temporaryException = false
    }
}
