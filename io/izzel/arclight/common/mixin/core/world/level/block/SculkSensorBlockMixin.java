/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.SculkSensorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SculkSensorBlock.class})
public class SculkSensorBlockMixin {
    @Unique
    private int newCurrent;

    @Inject(method={"stepOn"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;getBlockEntity(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;")})
    private void arclight$stepOn(Level level, BlockPos pos, BlockState p_222134_, Entity entity, CallbackInfo ci) {
        Event cancellable;
        if (entity instanceof Player) {
            cancellable = CraftEventFactory.callPlayerInteractEvent((Player)entity, Action.PHYSICAL, pos, null, null, null);
        } else {
            cancellable = new EntityInteractEvent(((EntityBridge)entity).bridge$getBukkitEntity(), CraftBlock.at((LevelAccessor)level, pos));
            Bukkit.getPluginManager().callEvent((EntityInteractEvent)cancellable);
        }
        if (cancellable.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"deactivate"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$deactivate(Level level, BlockPos pos, BlockState state, CallbackInfo ci) {
        BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(CraftBlock.at((LevelAccessor)level, pos), (Integer)state.m_61143_((Property)SculkSensorBlock.f_154387_), 0);
        Bukkit.getPluginManager().callEvent(eventRedstone);
        if (eventRedstone.getNewCurrent() > 0) {
            level.m_7731_(pos, (BlockState)state.m_61124_((Property)SculkSensorBlock.f_154387_, (Comparable)Integer.valueOf(eventRedstone.getNewCurrent())), 3);
            ci.cancel();
        }
    }

    @Inject(method={"activate"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$activate(Entity p_222126_, Level level, BlockPos pos, BlockState state, int i, int j, CallbackInfo ci) {
        BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(CraftBlock.at((LevelAccessor)level, pos), (Integer)state.m_61143_((Property)SculkSensorBlock.f_154387_), i);
        Bukkit.getPluginManager().callEvent(eventRedstone);
        if (eventRedstone.getNewCurrent() <= 0) {
            ci.cancel();
        }
        this.newCurrent = eventRedstone.getNewCurrent();
    }

    @ModifyVariable(method={"activate"}, ordinal=0, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"), argsOnly=true)
    private int arclight$updateCurrent(int old) {
        return this.newCurrent;
    }
}

