/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.BaseFireBlock
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.dimension.LevelStem
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.LevelStem;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={BaseFireBlock.class})
public class BaseFireBlockMixin {
    @Redirect(method={"entityInside"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;setSecondsOnFire(I)V"))
    private void arclight$onFire(Entity instance, int seconds, BlockState state, Level level, BlockPos pos) {
        EntityCombustByBlockEvent event = new EntityCombustByBlockEvent(CraftBlock.at((LevelAccessor)level, pos), ((EntityBridge)instance).bridge$getBukkitEntity(), seconds);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            ((EntityBridge)instance).bridge$setOnFire(event.getDuration(), false);
        }
    }

    @Redirect(method={"onPlace"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
    public boolean arclight$extinguish2(Level world, BlockPos pos, boolean isMoving) {
        if (!CraftEventFactory.callBlockFadeEvent((LevelAccessor)world, pos, Blocks.f_50016_.m_49966_()).isCancelled()) {
            world.m_7471_(pos, isMoving);
        }
        return false;
    }

    @Overwrite
    private static boolean m_49248_(Level level) {
        ResourceKey<LevelStem> typeKey = ((WorldBridge)level).bridge$getTypeKey();
        return typeKey == LevelStem.f_63972_ || typeKey == LevelStem.f_63971_;
    }
}

