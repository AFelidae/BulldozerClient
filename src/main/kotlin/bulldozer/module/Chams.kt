package bulldozer.module

import bulldozer.Module
import bulldozer.gui.SettingBoolean
import bulldozer.utils.EntityType
import bulldozer.utils.Typer
import net.minecraft.entity.Entity

class Chams : Module("Chams", arrayOf(
    SettingBoolean("Player", true),
    SettingBoolean("Other", false)), true){


    fun shouldSee(e: Entity) : Boolean{
        if(!toggled) return false
        when(Typer.entity(e)){
            EntityType.USER -> {
                if((settings[0] as SettingBoolean).value) return true
            }
            EntityType.OTHER -> {
                if((settings[1] as SettingBoolean).value) return true
            }
        }
        return false
    }
}
