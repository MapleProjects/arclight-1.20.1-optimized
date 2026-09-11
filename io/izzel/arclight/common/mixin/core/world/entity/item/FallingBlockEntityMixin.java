/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.FallingBlockEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.item;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={FallingBlockEntity.class})
public abstract class FallingBlockEntityMixin
extends EntityMixin {
    @Shadow
    private BlockState f_31946_;

    @Shadow
    public static FallingBlockEntity m_201971_(Level p_201972_, BlockPos p_201973_, BlockState p_201974_) {
        return null;
    }

    @Inject(method={"tick"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z")})
    private void arclight$entityChangeBlock(CallbackInfo ci, Block block, BlockPos pos) {
        if (!CraftEventFactory.callEntityChangeBlockEvent((Entity)((FallingBlockEntity)this), pos, this.f_31946_)) {
            ci.cancel();
        }
    }

    @Inject(method={"causeFallDamage"}, at={@At(value="INVOKE", remap=false, target="Ljava/util/List;forEach(Ljava/util/function/Consumer;)V")})
    private void arclight$damageSource(float distance, float damageMultiplier, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        CraftEventFactory.entityDamage = (FallingBlockEntity)this;
    }

    @Inject(method={"causeFallDamage"}, at={@At(value="INVOKE", remap=false, shift=At.Shift.AFTER, target="Ljava/util/List;forEach(Ljava/util/function/Consumer;)V")})
    private void arclight$damageSourceReset(float distance, float damageMultiplier, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        CraftEventFactory.entityDamage = null;
    }

    @Inject(method={"fall"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z")})
    private static void arclight$entityFall(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<FallingBlockEntity> cir, FallingBlockEntity entity) {
        if (!CraftEventFactory.callEntityChangeBlockEvent((Entity)entity, pos, state.m_60819_().m_76188_())) {
            cir.setReturnValue((Object)entity);
        }
    }

    private static FallingBlockEntity fall(Level level, BlockPos pos, BlockState state, CreatureSpawnEvent.SpawnReason spawnReason) {
        ((WorldBridge)level).bridge$pushAddEntityReason(spawnReason);
        return FallingBlockEntityMixin.m_201971_(level, pos, state);
    }
}

