package bulldozer

import bulldozer.events.KeyPress
import com.google.common.eventbus.Subscribe
import net.minecraft.client.MinecraftClient

open class Module {
    var toggled = false
    var key = -2
    var name: String
    var settings: Array<Any>
    var mc: MinecraftClient = MinecraftClient.getInstance()

    constructor(n: String, s: Array<Any>){
        name = n
        settings = s
        Manager.eventSystem.register(this)
    }

    fun toggle(){
        toggled = !toggled
        if(toggled) onEnable()
        else onDisable()
    }

    @Subscribe
    fun onKey(event: KeyPress){
        if(key == event.key) {
            toggle()
            event.setCancelled(true)
        }
    }

    open fun onEnable(){}
    open fun onDisable(){}
}
