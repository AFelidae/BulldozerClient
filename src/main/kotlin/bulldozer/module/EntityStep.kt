package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingFloat
import com.google.common.eventbus.Subscribe
import net.minecraft.entity.Entity

class EntityStep: Module("EntityStep", arrayOf<Any>(SettingFloat("Height",2f, 1f, 10f))) {
    @Subscribe
    fun onTick(event: Tick) {
        var e: Entity? = mc.player!!.vehicle ?: return
        if(!toggled) e!!.stepHeight = (settings[0] as SettingFloat).value;
        else e!!.stepHeight = 1f;
    }
}