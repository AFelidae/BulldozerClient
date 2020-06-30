package bulldozer.gui

import bulldozer.Manager
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import bulldozer.Module

class ClickGui : Screen {
    private var tabs: ArrayList<Tab> = ArrayList<Tab>();
    private var scroll = 0

    constructor() : super(LiteralText("Bulldozer Gui")) {
        for(i in 0..25){
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
        return if(num in 1..26) alphabet.substring(num,num+1) else " "
    }

    override fun isPauseScreen(): Boolean {
        return false
    }

    override fun render(matrix: MatrixStack?, mouseX: Int, mouseY: Int, partialTicks: Float) {
        if(scroll < 0) scroll = 0
        this.renderBackground(matrix)
        super.render(matrix, mouseX, mouseY, partialTicks)

        var fromTop = -scroll
        for(tab in tabs){
            fromTop += tab.render(matrix,mouseX,mouseY,fromTop)
        }
        // widthOfSettings =
    }

    /*

    fun drawBindSetting(matrix: MatrixStack?, m: Module, key: Int, x: Int, y: Int) {
        fill(matrix, x, y + 11, x + len - 2, y + 12, -0x6f4f4f50)
        fill(matrix, x + len - 2, y, x + len - 1, y + 12, -0x6f4f4f50)
        fill(matrix, x, y - 1, x + 1, y + 11, -0x70000000)
        if (key != -1 && mouseOver(x, y, x + len, y + 12)) m.setKey(if (key != 261 && key != 256) key else -1)
        var name = InputUtil.fromKeyCode(m.getKey(), -1).localizedText.asString()
        if (name == null) name = "KEY" + m.getKey()
        if (name.isEmpty()) name = "NONE"
        textRenderer.drawWithShadow(
            matrix, "Bind: " + name + if (mouseOver(x, y, x + len, y + 12)) "..." else ""
            , x + 2.toFloat(), y + 2.toFloat(), if (mouseOver(x, y, x + len, y + 12)) 0xcfc3cf else 0xcfe0cf
        )
    }

    fun drawModeSetting(matrix: MatrixStack?, s: SettingMode, x: Int, y: Int) {
        fillGreySides(matrix, x, y - 1, x + len - 1, y + 12)
        textRenderer.drawWithShadow(
            matrix, s.text + s.modes.get(s.mode), x + 2, y + 2,
            if (mouseOver(x, y, x + len, y + 12)) 0xcfc3cf else 0xcfe0cf
        )
        if (mouseOver(x, y, x + len, y + 12) && lmDown) s.mode = s.getNextMode()
    }

    fun drawToggleSetting(matrix: MatrixStack?, s: SettingToggle, x: Int, y: Int) {
        val color2: String
        color2 = if (s.state) {
            if (mouseOver(x, y, x + len, y + 12)) "\u00a72" else "\u00a7a"
        } else {
            if (mouseOver(x, y, x + len, y + 12)) "\u00a74" else "\u00a7c"
        }
        fillGreySides(matrix, x, y - 1, x + len - 1, y + 12)
        textRenderer.drawWithShadow(matrix, color2 + s.text, x + 3, y + 2, -1)
        if (mouseOver(x, y, x + len, y + 12) && lmDown) s.state = !s.state
    }

    fun drawSliderSetting(matrix: MatrixStack?, s: SettingSlider, x: Int, y: Int) {
        val pixels = Math.round(
            MathHelper.clamp(
                (len - 2) * ((s.getValue() - s.min) / (s.max - s.min)),
                0,
                len - 2
            ).toDouble()
        ).toInt()
        fillGreySides(matrix, x, y - 1, x + len - 1, y + 12)
        fillGradient(matrix, x + 1, y, x + pixels, y + 12, -0xfcf7f60, -0xfdf8f50)
        textRenderer.drawWithShadow(
            matrix,
            s.text.toString() + if (s.round === 0 && s.getValue() > 100) Integer.toString(s.getValue() as Int) else s.getValue(),
            x + 2.toFloat(),
            y + 2.toFloat(),
            if (mouseOver(x, y, x + len, y + 12)) 0xcfc3cf else 0xcfe0cf
        )
        if (mouseOver(x + 1, y, x + len - 2, y + 12) && lmHeld) {
            val percent: Int = (mouseX - x) * 100 / (len - 2)
            s.setValue(s.round(percent * ((s.max - s.min) / 100) + s.min, s.round))
        }
    }

    private fun cutText(text: String, leng: Int): String? {
        var text1 = text
        for (i in 0 until text.length) {
            if (textRenderer.getWidth(text1) < len - 2) return text1
            text1 = text1.replace(".$".toRegex(), "")
        }
        return ""
    }

    protected fun mouseOver(minX: Int, minY: Int, maxX: Int, maxY: Int): Boolean {
        return mouseX >= minX && mouseX <= maxX && mouseY >= minY && mouseY < maxY
    }

    fun fillReverseGrey(matrix: MatrixStack?, x1: Int, y1: Int, x2: Int, y2: Int) {
        fill(matrix, x1, y1, x1 + 1, y2 - 1, -0x70000000)
        fill(matrix, x1 + 1, y1, x2 - 1, y1 + 1, -0x70000000)
        fill(matrix, x2 - 2, y1 + 1, x2, y2, -0x6f4f4f50)
    }

    fun fillGreySides(matrix: MatrixStack?, x1: Int, y1: Int, x2: Int, y2: Int) {
        fill(matrix, x1, y1, x1 + 1, y2 - 1, -0x70000000)
        fill(matrix, x2 - 1, y1 + 1, x2, y2, -0x6f4f4f50)
    }

    override fun mouseClicked(
        p_mouseClicked_1_: Double,
        p_mouseClicked_3_: Double,
        p_mouseClicked_5_: Int
    ): Boolean {
        if (p_mouseClicked_5_ == 0) {
            lmDown = true
            lmHeld = true
        } else if (p_mouseClicked_5_ == 1) rmDown = true
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)
    }

    override fun mouseReleased(double_1: Double, double_2: Double, int_1: Int): Boolean {
        if (int_1 == 0) lmHeld = false
        return super.mouseReleased(double_1, double_2, int_1)
    }

    override fun keyPressed(int_1: Int, int_2: Int, int_3: Int): Boolean {
        keyDown = int_1
        return super.keyPressed(int_1, int_2, int_3)
    }*/
}