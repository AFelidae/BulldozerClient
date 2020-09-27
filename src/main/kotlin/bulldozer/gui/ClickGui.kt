package bulldozer.gui

import bulldozer.Manager
import bulldozer.Manager.getModule
import bulldozer.Module
import bulldozer.module.Gui
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import kotlin.math.round

class ClickGui : Screen {
    private var tabs: ArrayList<Tab> = ArrayList<Tab>();
    private var scroll = 0
    private var leftClick = false
    private var rightClick = false
    private var leftHeld = false
    private var rightHeld = false

    constructor() : super(LiteralText("Bulldozer Gui")) {
        var gui = getModule(Gui::class.java) as Gui
        if(!gui.toggled) gui.toggled = true
        for(i in 1..26){
            var letter = getCharForNumber(i)
            var letterMods: ArrayList<Module> = ArrayList<Module>()
            for(mod in Manager.modules){
                if(mod.name.toLowerCase().startsWith(letter.toLowerCase())) letterMods.add(mod)
            }
            tabs.add(Tab(letter,letterMods))
        }
    }

    private fun getCharForNumber(num: Int): String {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        return if(num in 1..26) alphabet.substring(num-1,num) else " "
    }

    override fun isPauseScreen(): Boolean {
        return false
    }

    override fun render(matrix: MatrixStack?, mouseX: Int, mouseY: Int, partialTicks: Float) {
        val mc: MinecraftClient = net.minecraft.client.MinecraftClient.getInstance()

        if(scroll < 0) scroll = 0
        this.renderBackground(matrix)
        super.render(matrix, mouseX, mouseY, partialTicks)

        var fromTop = -scroll
        for(tab in tabs){
            fromTop += tab.render(matrix,mouseX,mouseY,fromTop,leftClick,rightClick)
        }

        var gui = getModule(Gui::class.java) as Gui
        if(gui.selected != null){
            for(n in gui.selected!!.settings.indices){
                var over: Boolean = false
                if(mouseY in (n*20)..((n+1)*20) && mouseX > mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100)) over = true;

                Screen.fill(matrix, mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100) , n*20, mc.window.scaledWidth, (n+1)*20,  if(over) 0x70444444 else 0x70222222)
                when(gui.selected!!.settings[n]){
                    is SettingBoolean -> {
                        val s: SettingBoolean = gui.selected!!.settings[n] as SettingBoolean
                        if(leftClick && over) s.value = !s.value
                        if(s.value) Screen.fill(matrix, mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100) , n*20, mc.window.scaledWidth, (n+1)*20,  if(over) 0x70CC4444 else 0x70CC2222)
                        mc.textRenderer.drawWithShadow(matrix, s.name + ": " + s.value, (mc.window.scaledWidth - 140 - (mc.window.scaledWidth % 100)).toFloat(), ((n*20)+6).toFloat(), 0xFFFFFF);
                    }
                    is SettingDouble -> {
                        val s: SettingDouble = gui.selected!!.settings[n] as SettingDouble
                        if(leftHeld && over){
                            val percent: Float = (mouseX - (mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100))).toFloat()/(150 + (mc.window.scaledWidth % 100)).toFloat()
                            s.value = (s.maximum - s.minimum) * percent + s.minimum
                        }
                        val percent = 1 - (s.value - s.minimum)/(s.maximum-s.minimum)
                        Screen.fill(matrix, mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100) , n*20, (mc.window.scaledWidth + ((- 150 - (mc.window.scaledWidth % 100))*percent)).toInt() , (n+1)*20,  if(over) 0x70CC4444 else 0x70CC2222)
                        mc.textRenderer.drawWithShadow(matrix, s.name + ": " + round((s.value * 1000).toDouble())/1000, (mc.window.scaledWidth - 140 - (mc.window.scaledWidth % 100)).toFloat(), ((n*20)+6).toFloat(), 0xFFFFFF);
                    }
                    is SettingFloat -> {
                        val s: SettingFloat = gui.selected!!.settings[n] as SettingFloat
                        if(leftHeld && over){
                            val percent: Float = (mouseX - (mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100))).toFloat()/(150 + (mc.window.scaledWidth % 100)).toFloat()
                            s.value = (s.maximum - s.minimum) * percent + s.minimum
                        }
                        val percent = 1 - (s.value - s.minimum)/(s.maximum-s.minimum)
                        Screen.fill(matrix, mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100) , n*20, (mc.window.scaledWidth + ((- 150 - (mc.window.scaledWidth % 100))*percent)).toInt() , (n+1)*20,  if(over) 0x70CC4444 else 0x70CC2222)
                        mc.textRenderer.drawWithShadow(matrix, s.name + ": " + round((s.value * 10).toDouble())/10, (mc.window.scaledWidth - 140 - (mc.window.scaledWidth % 100)).toFloat(), ((n*20)+6).toFloat(), 0xFFFFFF);

                    }
                    is SettingInt -> {
                        val s: SettingInt = gui.selected!!.settings[n] as SettingInt
                        if(leftHeld && over){
                            val percent: Float = (mouseX - (mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100))).toFloat()/(150 + (mc.window.scaledWidth % 100)).toFloat()
                            s.value = ((s.maximum - s.minimum) * percent + s.minimum).toInt()
                        }
                        val percent = 1 - (s.value - s.minimum)/(s.maximum-s.minimum)
                        Screen.fill(matrix, mc.window.scaledWidth - 150 - (mc.window.scaledWidth % 100) , n*20, (mc.window.scaledWidth + ((- 150 - (mc.window.scaledWidth % 100))*percent)).toInt() , (n+1)*20,  if(over) 0x70CC4444 else 0x70CC2222)
                        mc.textRenderer.drawWithShadow(matrix, s.name + ": " + s.value, (mc.window.scaledWidth - 140 - (mc.window.scaledWidth % 100)).toFloat(), ((n*20)+6).toFloat(), 0xFFFFFF);
                    }
                    is SettingMode -> {
                        val s: SettingMode = gui.selected!!.settings[n] as SettingMode
                        if(leftClick && over){
                            s.value += 1
                            s.value == s.value % s.modes.size
                        }
                        mc.textRenderer.drawWithShadow(matrix, s.name + ": " + s.modes[s.value], (mc.window.scaledWidth - 140 - (mc.window.scaledWidth % 100)).toFloat(), ((n*20)+6).toFloat(), 0xFFFFFF);

                    }
                    is SettingString -> {
                        val s: SettingString = gui.selected!!.settings[n] as SettingString
                        mc.textRenderer.drawWithShadow(matrix, s.name + ": " + s.value, (mc.window.scaledWidth - 140 - (mc.window.scaledWidth % 100)).toFloat(), ((n*20)+6).toFloat(), 0xFFFFFF);
                    }
                }
            }
        }

        leftClick = false
        rightClick = false
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean {
        // scrollbar
        scroll -= delta.toInt() * 10
        return super.mouseScrolled(mouseX, mouseY, delta)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (button == 0) {
            leftClick = true
            leftHeld = true
        } else if(button == 1) {
            rightClick = true
            rightHeld = true
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (button == 0) {
            leftClick = false
            leftHeld = false
        } else if(button == 1) {
            rightClick = false
            rightHeld = false
        }
        return super.mouseReleased(mouseX, mouseY, button)
    }

    override fun onClose() {
        val gui = getModule(Gui::class.java) as Gui
        gui.toggled = false
        super.onClose()
    }
}