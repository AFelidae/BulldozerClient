package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingFloat
import bulldozer.utils.DrawUtil
import com.google.common.eventbus.Subscribe
import net.minecraft.entity.player.PlayerEntity

class Tracers : Module("Tracers", arrayOf(
    SettingFloat("Opacity", 0.3f, 0f, 1f)), true){

    @Subscribe
    fun onRender(event: Render3D){
        val opacity = (settings[0] as SettingFloat).value
        var wide = 2.5f
        for(e in mc.world!!.entities){
            if(!e.isAttackable) continue
            if(e is PlayerEntity && e != mc.player) DrawUtil.entityLine(e,1f, 0f, 0f, opacity, 1f)
        }
    }
}