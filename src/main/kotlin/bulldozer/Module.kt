package bulldozer

import bulldozer.events.KeyPress
import bulldozer.gui.SettingGenericBase
import bulldozer.utils.Chat
import com.google.common.eventbus.Subscribe
import net.minecraft.client.MinecraftClient
import java.lang.Exception

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

    fun getSettingByName(name:String): Any?{
        try {
            for (set in settings) {
                val s = set as SettingGenericBase
                if (Chat.compare(name, s.name)) {
                    return set
                }
            }
        }catch(err: Exception){}
        return null
    }

    open fun onEnable(){}
    open fun onDisable(){}
}
