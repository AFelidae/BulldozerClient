package bulldozer.module

import bulldozer.Module

class Mute: Module("Mute", emptyArray(), true){
    public var temporaryException: Boolean = false
    //ClientConnection.java
    
    override fun onEnable() {
        temporaryException = false
    }
}
