package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket
import net.minecraft.screen.slot.SlotActionType


class ShovelSpam: Module("ShovelSpam", emptyArray<Any>(), true) {
    @Subscribe
    fun onTick(event: Tick) {
        if(mc.currentScreen != null) return
        if(mc.player!!.isCreative){
            for (i in 0..35) {
                if(!mc.player!!.inventory.getStack(i).isEmpty) continue
                val stack = ItemStack(Items.WOODEN_SHOVEL)
                mc.player!!.networkHandler.sendPacket(CreativeInventoryActionC2SPacket(i, stack))
            }
        }else{
            //TODO: Survival mode with crafting
        }
        for (i in 0..35) {
            if(mc.player!!.inventory.getStack(i).item != Items.WOODEN_SHOVEL) continue
            mc.interactionManager!!.clickSlot(
                mc.player!!.currentScreenHandler.syncId,
                if (i < 9) i + 36 else i,
                0,
                SlotActionType.PICKUP,
                mc.player
            )
            mc.interactionManager!!.clickSlot(
                mc.player!!.currentScreenHandler.syncId,
                -999,
                0,
                SlotActionType.PICKUP,
                mc.player
            )
        }
    }
}