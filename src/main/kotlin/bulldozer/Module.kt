package bulldozer

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

        for (method in javaClass.methods) {
            if (method.isAnnotationPresent(Subscribe::class.java)) {
                Manager.eventSystem.register(this)
                break
            }
        }
    }

    fun toggle(){
        toggled = !toggled
        if(toggled) onEnable()
        else onDisable()
    }

    open fun onEnable(){}
    open fun onDisable(){}
}
