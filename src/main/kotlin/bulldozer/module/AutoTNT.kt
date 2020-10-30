package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import bulldozer.utils.DrawUtil
import bulldozer.utils.TNTUtils
import bulldozer.utils.TNTUtils.nextMove
import com.google.common.eventbus.Subscribe
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import kotlin.math.abs
import kotlin.math.floor

class AutoTNT: Module("AutoTNT", arrayOf<Any>(SettingBoolean("Debugging mode", true), SettingBoolean("Movement", false)), true){
    @Subscribe
    fun render(event: Render3D){
        if(!toggled) return
        val state = nextMove((settings[0] as SettingBoolean).value)

    }


    @Subscribe
    fun onTick(event: Tick){
        if(!toggled) return


        if(!(settings[1] as SettingBoolean).value) return
        //Actual movement stuff would be done here
    }

    override fun onDisable() {
        super.onDisable()
        TNTUtils.blacklist = HashSet<BlockPos>()
    }
}