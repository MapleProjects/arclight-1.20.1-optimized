/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.TurtleEggBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={TurtleEggBlock.class})
public class TurtleEggBlockMixin {
    @Shadow
    @Final
    public static IntegerProperty f_57753_;

    @Inject(method={"randomTick"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/server/level/ServerLevel;playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V")})
    private void arclight$hatch(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random, CallbackInfo ci, int i) {
        if (!CraftEventFactory.handleBlockGrowEvent((Level)worldIn, pos, (BlockState)state.m_61124_((Property)f_57753_, (Comparable)Integer.valueOf(i + 1)), 2)) {
            ci.cancel();
        }
    }

    @Redirect(method={"randomTick"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean arclight$handledHatch(ServerLevel world, BlockPos pos, BlockState newState, int flags) {
        return true;
    }

    @Inject(method={"randomTick"}, cancellable=true, at={@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/server/level/ServerLevel;playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V")})
    private void arclight$born(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (CraftEventFactory.callBlockFadeEvent((LevelAccessor)worldIn, pos, Blocks.f_50016_.m_49966_()).isCancelled()) {
            ci.cancel();
        } else {
            ((WorldBridge)worldIn).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.EGG);
        }
    }

    @Inject(method={"destroyEgg"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/block/TurtleEggBlock;decreaseEggs(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V")})
    public void arclight$stepOn(Level world, BlockState state, BlockPos pos, Entity entity, int i, CallbackInfo ci) {
        Event cancellable;
        if (entity instanceof Player) {
            cancellable = CraftEventFactory.callPlayerInteractEvent((Player)entity, Action.PHYSICAL, pos, null, null, null);
        } else {
            cancellable = new EntityInteractEvent(((EntityBridge)entity).bridge$getBukkitEntity(), CraftBlock.at((LevelAccessor)world, pos));
            Bukkit.getPluginManager().callEvent((EntityInteractEvent)cancellable);
        }
        if (cancellable.isCancelled()) {
            ci.cancel();
        }
    }
}

