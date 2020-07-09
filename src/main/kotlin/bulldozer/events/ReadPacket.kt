package bulldozer.events

import bulldozer.Event
import net.minecraft.network.Packet

class ReadPacket(packet_1: Packet<*>) : Event() {
    val packet = packet_1
}