package bulldozer.module

import bulldozer.Module
import bulldozer.gui.SettingFloat

class Timer : Module("Timer", arrayOf<Any>(SettingFloat("Speed", 1.3f, 0.5f, 2.0f)), true) {} //RenderTickCounter.java