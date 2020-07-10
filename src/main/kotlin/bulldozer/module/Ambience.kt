package bulldozer.module

import bulldozer.Module
import bulldozer.events.ReadPacket
import bulldozer.events.Tick
import bulldozer.gui.*

import com.google.common.eventbus.Subscribe
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket

class Ambience : Module("Ambience", arrayOf(
    SettingBoolean("LockWeather", true),
    SettingBoolean("LockTime", false),
    SettingMode("Weather",arrayOf("Clear", "Rain")),
    SettingFloat("Visibility", 0f, 0f, 2f),
    SettingInt("Time",12500,0,24000)
)){
    @Subscribe
    fun onTick(event: Tick) {
        if ((settings[0] as SettingBoolean).value) {
            if ((settings[2] as SettingMode).value == 0) mc.world!!.setRainGradient(0f) else mc.world!!.setRainGradient((settings[3] as SettingFloat).value)
        }
        if ((settings[1] as SettingBoolean).value){
            val time: Long = (settings[4] as SettingInt).value.toLong()
            mc.world!!.timeOfDay = time
            mc.world!!.timeOfDay = time
        }
    }

    @Subscribe
    fun onReadPacket(event: ReadPacket) {
        if (event.packet is WorldTimeUpdateS2CPacket) {
            event.setCancelled(true)
        }
    }
}