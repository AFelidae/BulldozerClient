package bulldozer.events

import bulldozer.Event
import net.minecraft.network.Packet

class SendPacket(packet_1: Packet<*>) : Event() {
    val packet = packet_1
}