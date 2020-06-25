package bulldozer

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import java.util.function.Function
import com.google.common.eventbus.EventBus


@Suppress("unused")
class Client : ClientModInitializer {
    @kotlin.jvm.JvmField
    var eventSystem: EventBus = EventBus()
    override fun onInitializeClient() {

    }
}
