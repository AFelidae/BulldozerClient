package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import bulldozer.utils.DrawUtil
import bulldozer.utils.TNTUtils
import com.google.common.eventbus.Subscribe
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import kotlin.math.abs

class AutoTNT: Module("AutoTNT", arrayOf<Any>(SettingBoolean("Debugging mode", true), SettingBoolean("Movement", false)), true){
    var bestW: Double? = null
    var bestP: BlockPos? = null

    @Subscribe
    fun render(event: Render3D){
        if(!toggled) return

        if(mc.player!!.isOnGround) TNTUtils.blacklist[mc.player!!.blockPos.down(1)] = true
        var bestWeight: Double? = null
        var bestPos: BlockPos? = null
        for(x in -4..4){
            for(z in -4..4) {
                val blockPos = mc.player!!.blockPos.down(1).north(x).east(z)
                val b = mc.world!!.getBlockState(blockPos)
                if(b.isAir) continue
                if(!(b.isOf(Blocks.SAND) || b.isOf(Blocks.GRAVEL) || b.isOf(Blocks.RED_SAND))) continue
                if(TNTUtils.blacklist[blockPos] != null){
                    DrawUtil.blockBox(blockPos, 1.0f, 0.0f, 0.0f, 0.3f)
                    continue
                }
                val weight = TNTUtils.getWeight(blockPos) - abs(x) - abs(z)
                if((settings[0] as SettingBoolean).value) DrawUtil.blockBox(blockPos, 1.0f, 1.0f, 1.0f, 0.3f)
                if(bestWeight == null){
                    bestPos = blockPos
                    bestWeight = weight
                }else{
                    if(bestWeight < weight){
                        bestWeight = weight
                        bestPos = blockPos
                    }
                }
            }
        }
        if(bestWeight != null)
            if((settings[0] as SettingBoolean).value) DrawUtil.blockBox(bestPos!!, 0.0f, 0.0f, 1.0f, 0.3f)
        bestW = bestWeight
        bestP = bestPos
    }

    @Subscribe
    fun onTick(event: Tick){
        if(!toggled) return

        if(!(settings[1] as SettingBoolean).value) return
        if(bestW != null && mc.player!!.isOnGround){
            TNTUtils.lookAt(bestP!!.x.toDouble() + 0.5, bestP!!.y.toDouble() + 0.5, bestP!!.z.toDouble() + 0.5)
            mc.player!!.jump()
        }
    }

    override fun onDisable() {
        TNTUtils.blacklist = HashMap<BlockPos, Boolean>()
    }
}