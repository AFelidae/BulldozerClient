package bulldozer.events

import bulldozer.Event
import net.minecraft.client.gui.screen.Screen

class OpenScreen(screen: Screen) : Event() {
    var openedScreen = screen
}