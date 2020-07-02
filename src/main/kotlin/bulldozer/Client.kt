package bulldozer

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import java.util.function.Function
import com.google.common.eventbus.EventBus
import kotlin.reflect.typeOf


@Suppress("unused")
class Client : ClientModInitializer {
    override fun onInitializeClient() {

    }
}
