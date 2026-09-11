/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.RedStoneOreBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={RedStoneOreBlock.class})
public abstract class RedstoneOreBlockMixin {
    private static transient Entity arclight$entity;

    @Shadow
    private static void m_55492_(BlockState state, Level world, BlockPos pos) {
    }

    @Inject(method={"attack"}, at={@At(value="HEAD")})
    public void arclight$interact1(BlockState state, Level worldIn, BlockPos pos, Player player, CallbackInfo ci) {
        arclight$entity = player;
    }

    @Inject(method={"stepOn"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$entityInteract(Level worldIn, BlockPos pos, BlockState state, Entity entityIn, CallbackInfo ci) {
        if (entityIn instanceof Player) {
            PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent((Player)entityIn, Action.PHYSICAL, pos, null, null, null);
            if (event.isCancelled()) {
                ci.cancel();
                return;
            }
        } else {
            EntityInteractEvent event = new EntityInteractEvent(((EntityBridge)entityIn).bridge$getBukkitEntity(), CraftBlock.at((LevelAccessor)worldIn, pos));
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
                return;
            }
        }
        arclight$entity = entityIn;
    }

    @Inject(method={"use"}, at={@At(value="HEAD")})
    public void arclight$interact3(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, CallbackInfoReturnable<Boolean> cir) {
        arclight$entity = player;
    }

    @Inject(method={"randomTick"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z")})
    private void arclight$blockFade(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (CraftEventFactory.callBlockFadeEvent((LevelAccessor)worldIn, pos, (BlockState)state.m_61124_((Property)RedStoneOreBlock.f_55450_, (Comparable)Boolean.valueOf(false))).isCancelled()) {
            ci.cancel();
        }
    }

    private static void interact(BlockState blockState, Level world, BlockPos blockPos, Entity entity) {
        arclight$entity = entity;
        RedstoneOreBlockMixin.m_55492_(blockState, world, blockPos);
    }

    @Inject(method={"interact"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z")})
    private static void arclight$entityChangeBlock(BlockState blockState, Level world, BlockPos blockPos, CallbackInfo ci) {
        if (!CraftEventFactory.callEntityChangeBlockEvent(arclight$entity, blockPos, (BlockState)blockState.m_61124_((Property)RedStoneOreBlock.f_55450_, (Comparable)Boolean.valueOf(true)))) {
            ci.cancel();
        }
        arclight$entity = null;
    }
}

