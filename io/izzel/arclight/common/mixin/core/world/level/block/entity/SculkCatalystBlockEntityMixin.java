/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.SculkCatalystBlockEntity
 *  net.minecraft.world.level.block.entity.SculkCatalystBlockEntity$CatalystListener
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.tileentity.SculkCatalystListenerBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.BlockEntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SculkCatalystBlockEntity.class})
public abstract class SculkCatalystBlockEntityMixin
extends BlockEntityMixin {
    @Shadow
    @Final
    private SculkCatalystBlockEntity.CatalystListener f_279609_;

    @Override
    public void m_142339_(Level p_155231_) {
        super.m_142339_(p_155231_);
        ((SculkCatalystListenerBridge)this.f_279609_).bridge$setLevel(p_155231_);
    }

    @Inject(method={"serverTick"}, at={@At(value="HEAD")})
    private static void arclight$overrideSource(Level p_222780_, BlockPos p_222781_, BlockState p_222782_, SculkCatalystBlockEntity blockEntity, CallbackInfo ci) {
        CraftEventFactory.sourceBlockOverride = blockEntity.m_58899_();
    }

    @Inject(method={"serverTick"}, at={@At(value="RETURN")})
    private static void arclight$resetSource(Level p_222780_, BlockPos p_222781_, BlockState p_222782_, SculkCatalystBlockEntity blockEntity, CallbackInfo ci) {
        CraftEventFactory.sourceBlockOverride = null;
    }

    @Inject(method={"load"}, at={@At(value="HEAD")})
    private void arclight$load(CompoundTag p_277597_, CallbackInfo ci) {
        super.m_142466_(p_277597_);
    }
}

