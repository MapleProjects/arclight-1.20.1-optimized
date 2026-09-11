/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.BigDripleafBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.Tilt
 *  net.minecraft.world.phys.BlockHitResult
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BigDripleafBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Tilt;
import net.minecraft.world.phys.BlockHitResult;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BigDripleafBlock.class})
public class BigDripleafBlockMixin {
    @Shadow
    @Final
    private static EnumProperty<Tilt> f_152201_;

    @Inject(method={"onProjectileHit"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$projectileHit(Level level, BlockState state, BlockHitResult hitResult, Projectile projectile, CallbackInfo ci) {
        if (!DistValidate.isValid((LevelAccessor)level) && CraftEventFactory.callEntityChangeBlockEvent((Entity)projectile, hitResult.m_82425_(), (BlockState)state.m_61124_(f_152201_, (Comparable)Tilt.FULL))) {
            ci.cancel();
        }
    }

    @Inject(method={"entityInside"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/block/BigDripleafBlock;setTiltAndScheduleTick(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/properties/Tilt;Lnet/minecraft/sounds/SoundEvent;)V")})
    private void arclight$entityInteract(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        Event cancellable;
        if (!DistValidate.isValid((LevelAccessor)level)) {
            return;
        }
        if (entity instanceof Player) {
            cancellable = CraftEventFactory.callPlayerInteractEvent((Player)entity, Action.PHYSICAL, pos, null, null, null);
        } else {
            cancellable = new EntityInteractEvent(((EntityBridge)entity).bridge$getBukkitEntity(), CraftBlock.at((LevelAccessor)level, pos));
            Bukkit.getPluginManager().callEvent((EntityInteractEvent)cancellable);
        }
        if (cancellable.isCancelled()) {
            ci.cancel();
            return;
        }
        if (!CraftEventFactory.callEntityChangeBlockEvent(entity, pos, (BlockState)state.m_61124_(f_152201_, (Comparable)Tilt.FULL))) {
            ci.cancel();
        }
    }
}

