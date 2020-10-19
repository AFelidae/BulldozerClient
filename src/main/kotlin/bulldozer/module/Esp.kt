package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingFloat
import bulldozer.utils.DrawUtil
import bulldozer.utils.EntityType
import bulldozer.utils.Typer
import com.google.common.eventbus.Subscribe

class Esp : Module("Esp", arrayOf(
    SettingBoolean("Animal", false),
    SettingBoolean("Monster", false),
    SettingBoolean("Player", true),
    SettingBoolean("Teammate", true),
    SettingBoolean("Friend", true),
    SettingBoolean("Item", true),
    SettingBoolean("Other", false),
    SettingFloat("Opacity", 0.3f, 0f, 1f),
    SettingBoolean("ShowInvisible", true)), true){

    @Subscribe
    fun onRender(event: Render3D){
        val opacity = (settings[7] as SettingFloat).value
        for(e in mc.world!!.entities){
            if(!e.isAttackable) continue
            if(e.isInvisible && !(settings[7] as SettingBoolean).value) continue
            when(Typer.entity(e)){
                EntityType.ANIMAL -> {
                    if((settings[0] as SettingBoolean).value) DrawUtil.entityBox(e,0f, 0.8f, 0f, opacity)
                }
                EntityType.MONSTER -> {
                    if((settings[1] as SettingBoolean).value) DrawUtil.entityBox(e,0.2f, 0.4f, 0f, opacity)
                }
                EntityType.PLAYER -> {
                    if((settings[2] as SettingBoolean).value) DrawUtil.entityBox(e,1f, 0f, 0f, opacity)
                }
                EntityType.TEAMMATE -> {
                    if((settings[3] as SettingBoolean).value) DrawUtil.entityBox(e,1f, 0.4f, 0f, opacity)
                }
                EntityType.FRIEND -> {
                    if((settings[4] as SettingBoolean).value) DrawUtil.entityBox(e,0f, 1f, 1f, opacity)
                }
                EntityType.ITEM -> {
                    if((settings[5] as SettingBoolean).value) DrawUtil.entityBox(e, 0f, 0.6f, 1f, opacity)
                }
                EntityType.OTHER -> {
                    if((settings[6] as SettingBoolean).value) DrawUtil.entityBox(e,1f, 1f, 1f, opacity)
                }
            }
        }
    }
}
