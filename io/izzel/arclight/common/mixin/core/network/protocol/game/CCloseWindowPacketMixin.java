/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.game.ServerboundContainerClosePacket
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.network.protocol.game;

import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ServerboundContainerClosePacket.class})
public class CCloseWindowPacketMixin {
    @Shadow
    @Final
    @Mutable
    private int f_133967_;

    public void arclight$constructor() {
        throw new RuntimeException();
    }

    public void arclight$constructor(int id) {
        this.arclight$constructor();
        this.f_133967_ = id;
    }
}

