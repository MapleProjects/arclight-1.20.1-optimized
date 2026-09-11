/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Component$Serializer
 *  net.minecraft.network.protocol.game.ClientboundSystemChatPacket
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.network.protocol.game;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientboundSystemChatPacket.class})
public class ClientboundSystemChatPacketMixin {
    private String content;

    public void arclight$constructor(Component content, boolean overlay) {
        throw new RuntimeException();
    }

    public void arclight$constructor(String content, boolean overlay) {
        this.arclight$constructor((Component)Component.Serializer.m_130701_((String)content), overlay);
    }

    public void arclight$constructor(BaseComponent[] content, boolean overlay) {
        this.arclight$constructor(ComponentSerializer.toString(content), overlay);
    }

    @Inject(method={"<init>(Lnet/minecraft/network/chat/Component;Z)V"}, at={@At(value="RETURN")})
    private void arclight$init(Component content, boolean overlay, CallbackInfo ci) {
        this.content = Component.Serializer.m_130703_((Component)content);
    }

    public String content() {
        return this.content;
    }
}

