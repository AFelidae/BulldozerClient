package bulldozer.utils

import bulldozer.Manager
import bulldozer.module.Mute
import net.minecraft.client.MinecraftClient
import net.minecraft.text.LiteralText

object Chat {
    @JvmStatic
    fun sendMessage(msg: String){
        val mute = Manager.getModule(Mute::class.java) as Mute?
        mute!!.temporaryException = true
        MinecraftClient.getInstance().player!!.sendChatMessage(msg)
    }
    
    @JvmStatic
    fun normalMessage(msg: String){
        MinecraftClient.getInstance().inGameHud.chatHud
            .addMessage(LiteralText(msg))
    }

    @JvmStatic
    fun clientMessage(msg: String){
        try {
            MinecraftClient.getInstance().inGameHud.chatHud
                .addMessage(LiteralText("§7[§6Bulldozer§7] $msg"))
        } catch (err:Exception) {}
    }

    @JvmStatic
    fun errorMessage(msg: String){
        try {
            MinecraftClient.getInstance().inGameHud.chatHud
                .addMessage(LiteralText("§c[§4Bulldozer§c] $msg"))
        } catch (err: Exception) {}
    }

    @JvmStatic
    fun compare(a: String, b: String): Boolean{
        return a.toLowerCase().replace(" ", "") == b.toLowerCase().replace(" ", "")
    }
}
