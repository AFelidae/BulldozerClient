package bulldozer

import net.minecraft.client.MinecraftClient

interface Module {
    var name: String
    var description: String
    var toggled: Boolean

    fun onDisable()
    fun onEnable()

    fun toggle(){
        toggled = !toggled
        if(toggled) onEnable()
        else onDisable()
    }
}
