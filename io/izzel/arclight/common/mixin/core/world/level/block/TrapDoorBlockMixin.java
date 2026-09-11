/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.TrapDoorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={TrapDoorBlock.class})
public class TrapDoorBlockMixin {
    @Redirect(method={"neighborChanged"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;hasNeighborSignal(Lnet/minecraft/core/BlockPos;)Z"))
    public boolean arclight$blockRedstone(Level world, BlockPos pos, BlockState state, Level worldIn, BlockPos blockPos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        boolean flag = world.m_276867_(pos);
        if (flag != (Boolean)state.m_61143_((Property)TrapDoorBlock.f_57516_)) {
            CraftBlock craftBlock = CraftBlock.at((LevelAccessor)world, pos);
            int power = craftBlock.getBlockPower();
            int oldPower = (Boolean)state.m_61143_((Property)TrapDoorBlock.f_57514_) != false ? 15 : 0;
            if (oldPower == 0 ^ power == 0 || blockIn.m_49966_().m_60803_()) {
                BlockRedstoneEvent event = new BlockRedstoneEvent(craftBlock, oldPower, power);
                Bukkit.getPluginManager().callEvent(event);
                return event.getNewCurrent() > 0;
            }
        }
        return flag;
    }
}

