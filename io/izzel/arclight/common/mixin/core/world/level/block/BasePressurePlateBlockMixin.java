/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.BasePressurePlateBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={BasePressurePlateBlock.class})
public abstract class BasePressurePlateBlockMixin {
    @Shadow
    @Final
    protected static AABB f_49287_;

    @Shadow
    protected abstract int m_6693_(Level var1, BlockPos var2);

    @Redirect(method={"checkPressed"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/BasePressurePlateBlock;getSignalStrength(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)I"))
    private int arclight$blockRedstone(BasePressurePlateBlock abstractPressurePlateBlock, Level worldIn, BlockPos pos, Entity entity, Level world, BlockPos blockPos, BlockState state, int oldRedstoneStrength) {
        boolean flag1;
        int newStrength = this.m_6693_(worldIn, pos);
        boolean flag = oldRedstoneStrength > 0;
        boolean bl = flag1 = newStrength > 0;
        if (flag != flag1 && DistValidate.isValid((LevelAccessor)world)) {
            BlockRedstoneEvent event = new BlockRedstoneEvent(CraftBlock.at((LevelAccessor)worldIn, blockPos), oldRedstoneStrength, newStrength);
            Bukkit.getPluginManager().callEvent(event);
            newStrength = event.getNewCurrent();
        }
        return newStrength;
    }
}

