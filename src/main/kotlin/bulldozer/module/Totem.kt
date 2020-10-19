package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import com.google.common.eventbus.Subscribe
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.item.Items
import net.minecraft.screen.slot.SlotActionType

class Totem: Module("Totem", emptyArray<Any>(), true) {
    @Subscribe
    fun onTick(event: Tick) {
        if (mc.player!!.offHandStack.item === Items.TOTEM_OF_UNDYING) return
        for (i in 0..35) {
            if (mc.player!!.inventory.getStack(i).item === Items.TOTEM_OF_UNDYING) {
                mc.interactionManager!!.clickSlot(
                    mc.player!!.currentScreenHandler.syncId,
                    if (i < 9) i + 36 else i,
                    0,
                    SlotActionType.PICKUP,
                    mc.player
                )
                mc.interactionManager!!.clickSlot(
                    mc.player!!.currentScreenHandler.syncId,
                    45,
                    0,
                    SlotActionType.PICKUP,
                    mc.player
                )
                return
            }
        }
    }
}