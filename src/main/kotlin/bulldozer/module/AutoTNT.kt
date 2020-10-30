package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.events.Tick
import bulldozer.utils.DrawUtil
import bulldozer.utils.TNTUtils
import com.google.common.eventbus.Subscribe
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos

class AutoTNT: Module("AutoTNT", emptyArray(), true){
    var bestW: Double? = null
    var bestP: BlockPos? = null

    @Subscribe
    fun render(event: Render3D){
        var bestWeight: Double? = null
        var bestPos: BlockPos? = null
        for(x in -4..4){
            for(z in -4..4) {
                if(x == 0 && z == 0) continue
                val blockPos = mc.player!!.blockPos.down(1).north(x).east(z)
                val b = mc.world!!.getBlockState(blockPos)
                if(b.isAir) continue
                if(!(b.isOf(Blocks.SAND) || b.isOf(Blocks.GRAVEL) || b.isOf(Blocks.RED_SAND))) continue
                val weight = TNTUtils.getWeight(blockPos)
                DrawUtil.blockBox(blockPos, 1.0f, 1.0f, 1.0f, 0.3f)
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
        bestW = bestWeight
        bestP = bestPos
    }

    @Subscribe
    fun onTick(event: Tick){
        if(bestW != null && mc.player!!.isOnGround){
            DrawUtil.blockBox(bestP!!, 1.0f, 0.0f, 0.0f, 0.3f)
            TNTUtils.lookAt(bestP!!.x.toDouble() + 0.5, bestP!!.y.toDouble() + 0.5, bestP!!.z.toDouble() + 0.5)
            mc.player!!.jump()
        }
    }
}