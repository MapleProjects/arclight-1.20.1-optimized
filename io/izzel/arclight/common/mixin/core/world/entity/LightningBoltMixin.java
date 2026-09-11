/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LightningBolt.class})
public abstract class LightningBoltMixin
extends EntityMixin {
    @Shadow
    private int f_20860_;
    public boolean isSilent = false;

    @Redirect(method={"tick"}, at=@At(value="FIELD", opcode=180, ordinal=0, target="Lnet/minecraft/world/entity/LightningBolt;life:I"))
    private int arclight$silent(LightningBolt lightningBolt) {
        return this.isSilent ? 0 : this.f_20860_;
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;thunderHit(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LightningBolt;)V")})
    private void arclight$captureEntity(CallbackInfo ci) {
        ArclightCaptures.captureDamageEventEntity((Entity)this);
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/Entity;thunderHit(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LightningBolt;)V")})
    private void arclight$resetEntity(CallbackInfo ci) {
        ArclightCaptures.captureDamageEventEntity(null);
    }

    @Redirect(method={"spawnFire"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean arclight$blockIgnite(Level world, BlockPos pos, BlockState state) {
        if (!CraftEventFactory.callBlockIgniteEvent(world, pos, (Entity)((LightningBolt)this)).isCancelled()) {
            return world.m_46597_(pos, state);
        }
        return false;
    }
}

