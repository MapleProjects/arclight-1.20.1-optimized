/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.shorts.ShortSet
 *  net.minecraft.core.SectionPos
 *  net.minecraft.network.protocol.game.ClientboundSectionBlocksUpdatePacket
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.network.protocol.game;

import it.unimi.dsi.fastutil.shorts.ShortSet;
import net.minecraft.core.SectionPos;
import net.minecraft.network.protocol.game.ClientboundSectionBlocksUpdatePacket;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ClientboundSectionBlocksUpdatePacket.class})
public class ClientboundSectionBlocksUpdatePacketMixin {
    @Shadow
    @Final
    @Mutable
    private SectionPos f_132980_;
    @Shadow
    @Final
    @Mutable
    private short[] f_132981_;
    @Shadow
    @Final
    @Mutable
    private BlockState[] f_132982_;

    public void arclight$constructor(SectionPos sectionposition, ShortSet shortset, BlockState[] states) {
        this.f_132980_ = sectionposition;
        this.f_132981_ = shortset.toShortArray();
        this.f_132982_ = states;
    }
}

