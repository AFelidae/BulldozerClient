package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.gui.Setting
import bulldozer.utils.DrawUtil
import bulldozer.utils.EntityType
import bulldozer.utils.Typer
import com.google.common.eventbus.Subscribe

class Esp : Module("Esp", arrayOf(
    Setting("Animal", false),
    Setting("Monster", false),
    Setting("Player", true),
    Setting("Teammate", true),
    Setting("Friend", true),
    Setting("Item", true),
    Setting("Other", false),
    Setting("Opacity", 0.3f, 0f, 1f)
    )){

    @Subscribe
    fun onRender(event: Render3D){
        if(!toggled) return
        var opacity = settings[7].value as Float
        for(e in mc.world!!.entities){
            when(Typer.entity(e)){
                EntityType.ANIMAL -> {
                    if(settings[0].value as Boolean) DrawUtil.entityBox(e,0f, 0.8f, 0f, opacity)
                }
                EntityType.MONSTER -> {
                    if(settings[1].value as Boolean) DrawUtil.entityBox(e,0.2f, 0.4f, 0f, opacity)
                }
                EntityType.PLAYER -> {
                    if(settings[2].value as Boolean) DrawUtil.entityBox(e,1f, 0f, 0f, opacity)
                }
                EntityType.TEAMMATE -> {
                    if(settings[3].value as Boolean) DrawUtil.entityBox(e,1f, 0.4f, 0f, opacity)
                }
                EntityType.FRIEND -> {
                    if(settings[4].value as Boolean) DrawUtil.entityBox(e,0f, 1f, 1f, opacity)
                }
                EntityType.ITEM -> {
                    if(settings[5].value as Boolean) DrawUtil.entityBox(e, 0f, 0.6f, 1f, opacity)
                }
                EntityType.OTHER -> {
                    if(settings[6].value as Boolean) DrawUtil.entityBox(e,1f, 1f, 1f, opacity)
                }
            }
        }
    }
}