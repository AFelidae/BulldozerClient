package bulldozer

import bulldozer.events.KeyPress
import bulldozer.gui.Setting
import com.google.common.eventbus.Subscribe
import net.minecraft.client.MinecraftClient

open class Module {
    var toggled = false
    var key = -2
    var name: String
    var description: String
    var settings: Array<Setting>
    var mc: MinecraftClient = MinecraftClient.getInstance()

    constructor(n: String, d: String, s: Array<Setting>){
        name = n
        description = d
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
