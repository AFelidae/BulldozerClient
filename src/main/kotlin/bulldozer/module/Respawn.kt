package bulldozer.module

import bulldozer.Module
import bulldozer.events.OpenScreen
import bulldozer.gui.SettingBoolean
import bulldozer.utils.Chat
import com.google.common.eventbus.Subscribe
import net.minecraft.client.gui.screen.DeathScreen
import kotlin.math.roundToInt

class Respawn: Module("Respawn", arrayOf(SettingBoolean("DeathSpot",true), SettingBoolean("Autospawn", true)), true) {
    @Subscribe
    fun onOpenScreen(event: OpenScreen){
        if(mc.player == null || event.openedScreen == null) return
        if(event.openedScreen is DeathScreen){
            if((settings[0] as SettingBoolean).value)
                Chat.clientMessage("You died @" + mc.player!!.x.roundToInt() + "," + mc.player!!.y.roundToInt() + "," + mc.player!!.z.roundToInt())
            if((settings[1] as SettingBoolean).value)
                mc.player!!.requestRespawn()
        }
    }
}