/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.game.ClientboundSetBorderCenterPacket
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.border.WorldBorder
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.network.protocol.game;

import io.izzel.arclight.common.bridge.core.world.border.WorldBorderBridge;
import net.minecraft.network.protocol.game.ClientboundSetBorderCenterPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ClientboundSetBorderCenterPacket.class})
public class SWorldBorderPacketMixin {
    @Shadow
    @Final
    @Mutable
    private double f_179211_;
    @Shadow
    @Final
    @Mutable
    private double f_179212_;

    @Inject(method={"<init>(Lnet/minecraft/world/level/border/WorldBorder;)V"}, at={@At(value="RETURN")})
    private void arclight$nether(WorldBorder border, CallbackInfo ci) {
        Level level = ((WorldBorderBridge)border).bridge$getWorld();
        this.f_179211_ = border.m_6347_() * (level != null ? level.m_6042_().f_63859_() : 1.0);
        this.f_179212_ = border.m_6345_() * (level != null ? level.m_6042_().f_63859_() : 1.0);
    }
}

