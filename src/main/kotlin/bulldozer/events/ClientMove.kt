package bulldozer.events

import bulldozer.Event
import net.minecraft.util.math.Vec3d

class ClientMove(next: Vec3d): Event() {
    val movement = next
}