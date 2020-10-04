package bulldozer.module

import bulldozer.Module
import bulldozer.gui.SettingDouble
import bulldozer.gui.SettingFloat

class CameraClip : Module("CameraClip", arrayOf<Any>(SettingDouble("Distance",5.0,2.0,20.0))) {

}