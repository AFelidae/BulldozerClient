package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.gui.Setting
import bulldozer.utils.ContainerType
import bulldozer.utils.DrawUtil
import bulldozer.utils.EntityType
import bulldozer.utils.Typer
import com.google.common.eventbus.Subscribe
import net.minecraft.entity.decoration.ItemFrameEntity
import kotlin.reflect.typeOf

class ChestEsp : Module("Chestesp", arrayOf(
    Setting("Chests", true),
    Setting("Redstone", true),
    Setting("Enderchests", true),
    Setting("Shulkers", true),
    Setting("Others", true),
    Setting("Entities", true),
    Setting("Opacity", 0.3f, 0f, 1f))){

    @Subscribe
    fun onRender(event: Render3D){
        if(!toggled) return
        var opacity = settings[6].value as Float
        for(block in mc.world!!.blockEntities){
            when(Typer.container(block)){
                ContainerType.CHEST -> {
                    if(settings[0].value as Boolean)
                        DrawUtil.blockBox(block.pos, 1f, 0.8f, 0f, opacity)
                }
                ContainerType.REDSTONE -> {
                    if(settings[1].value as Boolean)
                        DrawUtil.blockBox(block.pos, 0f, 0.6f, 0.6f, opacity)
                }
                ContainerType.ENDERCHEST -> {
                    if(settings[2].value as Boolean)
                    DrawUtil.blockBox(block.pos, 0.6f, 0.4f, 1f, opacity)
                }
                ContainerType.SHULKER -> {
                    if(settings[3].value as Boolean)
                    DrawUtil.blockBox(block.pos, 1f, 0.6f, 0.6f, opacity)
                }
                ContainerType.OTHER -> {
                    if(settings[4].value as Boolean)
                    DrawUtil.blockBox(block.pos,0.6f, 0.6f, 0.6f, opacity)
                }
            }
        }
        if(settings[5].value as Boolean){
            for(e in mc.world!!.entities){
                if(Typer.entity(e) == EntityType.STORAGE) DrawUtil.entityBox(e,1f, 1f, 0.6f, opacity)
            }
        }

    }
}