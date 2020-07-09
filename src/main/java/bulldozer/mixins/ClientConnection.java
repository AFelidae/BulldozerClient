package bulldozer.mixins;

import bulldozer.Manager;
import bulldozer.events.ReadPacket;
import bulldozer.events.SendPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.network.ClientConnection.class)
public class ClientConnection {
    @Shadow
    private Channel channel;

    @Shadow
    private void sendImmediately(Packet<?> packet_1, GenericFutureListener<? extends Future<? super Void>> genericFutureListener_1) {}

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void channelRead0(ChannelHandlerContext channelHandlerContext_1, Packet<?> packet_1, CallbackInfo callback) {
        if (this.channel.isOpen() && packet_1 != null) {
            try {
                ReadPacket event = new ReadPacket(packet_1);
                Manager.eventSystem.post(event);
                if (event.isCancelled()) callback.cancel();
            } catch (Exception exception) {}
        }
    }

    @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    public void send(Packet<?> packet_1, GenericFutureListener<? extends Future<? super Void>> genericFutureListener_1, CallbackInfo callback) {
        if (packet_1 instanceof ChatMessageC2SPacket) {
            ChatMessageC2SPacket pack = (ChatMessageC2SPacket) packet_1;
            if (pack.getChatMessage().startsWith("-")) {
                Manager.callCommand(pack.getChatMessage().substring(1).trim());
                callback.cancel();
            }
        }
        SendPacket event = new SendPacket(packet_1);
        Manager.eventSystem.post(event);

        if (event.isCancelled()) callback.cancel();
    }
}