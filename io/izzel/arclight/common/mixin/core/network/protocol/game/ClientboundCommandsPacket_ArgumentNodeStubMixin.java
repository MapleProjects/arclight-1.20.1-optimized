/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.commands.synchronization.ArgumentTypeInfo
 *  net.minecraft.commands.synchronization.ArgumentTypeInfo$Template
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.network.protocol.game;

import com.mojang.brigadier.arguments.ArgumentType;
import io.izzel.arclight.common.mod.ArclightMod;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.spigotmc.SpigotConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets={"net.minecraft.network.protocol.game.ClientboundCommandsPacket$ArgumentNodeStub"})
public class ClientboundCommandsPacket_ArgumentNodeStubMixin {
    private static final int ARCLIGHT_WRAP_INDEX = -256;

    @Inject(method={"serializeCap(Lnet/minecraft/network/FriendlyByteBuf;Lnet/minecraft/commands/synchronization/ArgumentTypeInfo;Lnet/minecraft/commands/synchronization/ArgumentTypeInfo$Template;)V"}, cancellable=true, at={@At(value="HEAD")})
    private static <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>> void arclight$wrapArgument(FriendlyByteBuf buf, ArgumentTypeInfo<A, T> type, ArgumentTypeInfo.Template<A> node, CallbackInfo ci) {
        if (!SpigotConfig.bungee) {
            return;
        }
        ResourceLocation key = ForgeRegistries.COMMAND_ARGUMENT_TYPES.getKey(type);
        if (key != null && (key.m_135827_().equals("minecraft") || key.m_135827_().equals("brigadier"))) {
            return;
        }
        ci.cancel();
        buf.m_130130_(-256);
        int id = BuiltInRegistries.f_256979_.m_7447_(type);
        if (id == -1) {
            ArclightMod.LOGGER.debug("Command argument type {} is not registered", type);
        }
        buf.m_130130_(id);
        FriendlyByteBuf payload = new FriendlyByteBuf(Unpooled.buffer());
        type.m_214155_(node, payload);
        buf.m_130130_(payload.readableBytes());
        buf.writeBytes((ByteBuf)payload);
    }
}

