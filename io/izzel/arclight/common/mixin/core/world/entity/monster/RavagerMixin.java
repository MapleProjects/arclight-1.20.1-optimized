/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.monster.Ravager
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Ravager.class})
public abstract class RavagerMixin
extends PathfinderMobMixin {
    @Redirect(method={"aiStep"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;destroyBlock(Lnet/minecraft/core/BlockPos;ZLnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$entityChangeBlock(Level world, BlockPos pos, boolean dropBlock, Entity entityIn) {
        return CraftEventFactory.callEntityChangeBlockEvent((Entity)((Ravager)this), pos, Blocks.f_50016_.m_49966_()) && world.m_46953_(pos, dropBlock, entityIn);
    }
}

