package bulldozer.utils

import net.minecraft.client.MinecraftClient
import net.minecraft.text.LiteralText

object Chat {
    fun normalMessage(msg: String){
        MinecraftClient.getInstance().inGameHud.chatHud
            .addMessage(LiteralText(msg))
    }

    fun clientMessage(msg: String){
        try {
            MinecraftClient.getInstance().inGameHud.chatHud
                .addMessage(LiteralText("§7[§6Bulldozer§7] $msg"))
        } catch (err:Exception) {}
    }

    fun errorMessage(msg: String){
        try {
            MinecraftClient.getInstance().inGameHud.chatHud
                .addMessage(LiteralText("§c[§4Bulldozer§c] $msg"))
        } catch (err: Exception) {}
    }

    fun compare(a: String, b: String): Boolean{
        return a.toLowerCase().replace(" ", "") == b.toLowerCase().replace(" ", "")
    }
}