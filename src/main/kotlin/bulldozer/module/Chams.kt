package bulldozer.module

import bulldozer.Module
import bulldozer.gui.SettingBoolean
import bulldozer.utils.EntityType
import bulldozer.utils.Typer
import net.minecraft.entity.Entity

class Chams : Module("Chams", arrayOf(
    SettingBoolean("Animal", false),
    SettingBoolean("Monster", false),
    SettingBoolean("Player", true),
    SettingBoolean("Teammate", true),
    SettingBoolean("Friend", true),
    SettingBoolean("Item", true),
    SettingBoolean("Other", false)), true){


    fun shouldSee(e: Entity) : Boolean{
        if(!toggled) return false
        when(Typer.entity(e)){
            EntityType.ANIMAL -> {
                if((settings[0] as SettingBoolean).value) return true
            }
            EntityType.MONSTER -> {
                if((settings[1] as SettingBoolean).value) return true
            }
            EntityType.PLAYER -> {
                if((settings[2] as SettingBoolean).value) return true
            }
            EntityType.TEAMMATE -> {
                if((settings[3] as SettingBoolean).value) return true
            }
            EntityType.FRIEND -> {
                if((settings[4] as SettingBoolean).value) return true
            }
            EntityType.ITEM -> {
                if((settings[5] as SettingBoolean).value) return true
            }
            EntityType.OTHER -> {
                if((settings[6] as SettingBoolean).value) return true
            }
        }
        return false
    }
}
