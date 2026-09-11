/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.ConcretePowderBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.mixin.core.world.level.block.FallingBlockMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.block.BlockFormEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ConcretePowderBlock.class})
public abstract class ConcretePowderBlockMixin
extends FallingBlockMixin {
    @Shadow
    @Final
    private BlockState f_52058_;

    @Redirect(method={"onLand"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    public boolean arclight$blockForm(Level world, BlockPos pos, BlockState newState, int flags) {
        return CraftEventFactory.handleBlockFormEvent(world, pos, newState, flags);
    }

    @Redirect(method={"getStateForPlacement"}, at=@At(value="FIELD", target="Lnet/minecraft/world/level/block/ConcretePowderBlock;concrete:Lnet/minecraft/world/level/block/state/BlockState;"))
    public BlockState arclight$blockForm(ConcretePowderBlock instance, BlockPlaceContext context) {
        Level world = context.m_43725_();
        BlockPos blockPos = context.m_8083_();
        CraftBlockState blockState = CraftBlockStates.getBlockState((LevelAccessor)world, blockPos);
        blockState.setData(this.f_52058_);
        BlockFormEvent event = new BlockFormEvent(blockState.getBlock(), blockState);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            return blockState.getHandle();
        }
        return super.m_5573_(context);
    }

    @Redirect(method={"updateShape"}, at=@At(value="FIELD", target="Lnet/minecraft/world/level/block/ConcretePowderBlock;concrete:Lnet/minecraft/world/level/block/state/BlockState;"))
    public BlockState arclight$blockForm(ConcretePowderBlock instance, BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!(worldIn instanceof Level)) {
            return this.f_52058_;
        }
        CraftBlockState blockState = CraftBlockStates.getBlockState(worldIn, currentPos);
        blockState.setData(this.f_52058_);
        BlockFormEvent event = new BlockFormEvent(blockState.getBlock(), blockState);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            return blockState.getHandle();
        }
        return super.m_7417_(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}

