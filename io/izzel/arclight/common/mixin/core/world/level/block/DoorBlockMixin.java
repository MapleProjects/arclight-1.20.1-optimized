/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.DoorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.DoubleBlockHalf
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={DoorBlock.class})
public abstract class DoorBlockMixin {
    @Shadow
    @Final
    public static EnumProperty<DoubleBlockHalf> f_52730_;
    @Shadow
    @Final
    public static BooleanProperty f_52729_;
    @Shadow
    @Final
    public static BooleanProperty f_52727_;

    @Shadow
    protected abstract void m_245755_(@Nullable Entity var1, Level var2, BlockPos var3, boolean var4);

    @Overwrite
    public void m_6861_(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        int oldPower;
        BlockPos blockPos = pos.m_121945_(state.m_61143_(f_52730_) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN);
        CraftBlock bukkitBlock = CraftBlock.at((LevelAccessor)worldIn, pos);
        CraftBlock blockTop = CraftBlock.at((LevelAccessor)worldIn, blockPos);
        int power = bukkitBlock.getBlockPower();
        int powerTop = blockTop.getBlockPower();
        if (powerTop > power) {
            power = powerTop;
        }
        if ((oldPower = (Boolean)state.m_61143_((Property)DoorBlock.f_52729_) != false ? 15 : 0) == 0 ^ power == 0) {
            boolean flag;
            BlockRedstoneEvent event = new BlockRedstoneEvent(bukkitBlock, oldPower, power);
            Bukkit.getPluginManager().callEvent(event);
            boolean bl = flag = event.getNewCurrent() > 0;
            if (flag != (Boolean)state.m_61143_((Property)f_52727_)) {
                this.m_245755_(null, worldIn, pos, flag);
            }
            worldIn.m_7731_(pos, (BlockState)((BlockState)state.m_61124_((Property)f_52729_, (Comparable)Boolean.valueOf(flag))).m_61124_((Property)f_52727_, (Comparable)Boolean.valueOf(flag)), 2);
        }
    }
}

