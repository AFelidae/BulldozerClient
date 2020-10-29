package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingFloat
import bulldozer.utils.DrawUtil
import bulldozer.utils.EntityType
import bulldozer.utils.Typer
import com.google.common.eventbus.Subscribe

class Tracers : Module("Tracers", arrayOf(
    SettingBoolean("Player", true),
    SettingBoolean("Other", false),
    SettingFloat("Opacity", 0.3f, 0f, 1f),
    SettingBoolean("ShowInvisible", true),
    SettingBoolean("DistanceSize", false)), true){

    @Subscribe
    fun onRender(event: Render3D){
        val opacity = (settings[2] as SettingFloat).value
        var wide = 2.5f
        for(e in mc.world!!.entities){
            if(!e.isAttackable) continue
            if(e.isInvisible && !((settings[3] as SettingBoolean).value)) continue
            if((settings[4] as SettingBoolean).value) wide = 5f - (mc.player!!.distanceTo(e)/5).toFloat()
            if(wide < 1f) wide = 1f
            when(Typer.entity(e)){
                EntityType.PLAYER -> {
                    if((settings[0] as SettingBoolean).value) DrawUtil.entityLine(e,1f, 0f, 0f, opacity, wide)
                }
                EntityType.OTHER -> {
                    if((settings[1] as SettingBoolean).value) DrawUtil.entityLine(e,1f, 1f, 1f, opacity, wide)
                }
            }
        }
    }
}