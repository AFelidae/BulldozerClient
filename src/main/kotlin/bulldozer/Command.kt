package bulldozer

import net.minecraft.client.MinecraftClient

interface Command {
    val syntax: String
    val aliases: Array<String>
    val mc: MinecraftClient
        get() = MinecraftClient.getInstance()

    fun getName(): String {
        return aliases[0]
    }

    fun onCommand(args: List<String>){}
}