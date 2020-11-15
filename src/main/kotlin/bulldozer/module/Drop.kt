package bulldozer.module

import bulldozer.Module
import bulldozer.events.Tick
import bulldozer.gui.SettingBoolean
import com.google.common.eventbus.Subscribe
import net.minecraft.item.Items
import net.minecraft.screen.slot.SlotActionType

class Drop : Module("Drop", arrayOf<Any>(SettingBoolean("DropHotbar", true)), true) {
    @Subscribe
    fun onTick(event: Tick) {
        if(mc.currentScreen != null) return

        val start = if((settings[0] as SettingBoolean).value) 0 else 9
        for (i in start..35) {
            if(mc.player!!.inventory.getStack(i).isEmpty) continue
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