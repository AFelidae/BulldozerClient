package bulldozer.module

import bulldozer.Module
import bulldozer.events.Render3D
import bulldozer.gui.SettingBoolean
import com.google.common.eventbus.Subscribe

import bulldozer.utils.DrawUtil
import kotlin.math.ceil
import kotlin.math.floor


class Highlight: Module("Highlight", arrayOf<Any>(SettingBoolean("IncludeFluids", true))) {
    @Subscribe
    fun onRender(event: Render3D){
        if(!toggled) return
        val target = mc.player!!.raycast(200.0,mc.tickDelta,(settings[0] as SettingBoolean).value).pos
        DrawUtil.drawBox(floor(target.x), floor(target.y), floor(target.z), ceil(target.x), ceil(target.y), ceil(target.z), 1f, 0f, 0f, 0.5f)
    }
}