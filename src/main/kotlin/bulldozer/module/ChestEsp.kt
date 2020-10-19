package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.gui.SettingBoolean
import bulldozer.gui.SettingFloat
import bulldozer.utils.ContainerType
import bulldozer.utils.DrawUtil
import bulldozer.utils.EntityType
import bulldozer.utils.Typer
import com.google.common.eventbus.Subscribe

class ChestEsp : Module("Chestesp", arrayOf(
    SettingBoolean("Chests", true),
    SettingBoolean("Redstone", true),
    SettingBoolean("Enderchests", true),
    SettingBoolean("Shulkers", true),
    SettingBoolean("Others", true),
    SettingBoolean("Entities", true),
    SettingFloat("Opacity", 0.3f, 0f, 1f)), true){

    @Subscribe
    fun onRender(event: Render3D){
        var opacity = (settings[6] as SettingFloat).value
        for(block in mc.world!!.blockEntities){
            when(Typer.container(block)){
                ContainerType.CHEST -> {
                    if((settings[0] as SettingBoolean).value)
                        DrawUtil.blockBox(block.pos, 1f, 0.8f, 0f, opacity)
                }
                ContainerType.REDSTONE -> {
                    if((settings[1] as SettingBoolean).value)
                        DrawUtil.blockBox(block.pos, 0f, 0.6f, 0.6f, opacity)
                }
                ContainerType.ENDERCHEST -> {
                    if((settings[2] as SettingBoolean).value)
                    DrawUtil.blockBox(block.pos, 0.6f, 0.4f, 1f, opacity)
                }
                ContainerType.SHULKER -> {
                    if((settings[3] as SettingBoolean).value)
                    DrawUtil.blockBox(block.pos, 1f, 0.6f, 0.6f, opacity)
                }
                ContainerType.OTHER -> {
                    if((settings[4] as SettingBoolean).value)
                    DrawUtil.blockBox(block.pos,0.6f, 0.6f, 0.6f, opacity)
                }
            }
        }
        if((settings[5] as SettingBoolean).value){
            for(e in mc.world!!.entities){
                if(Typer.entity(e) == EntityType.STORAGE) DrawUtil.entityBox(e,1f, 1f, 0.6f, opacity)
            }
        }

    }
}