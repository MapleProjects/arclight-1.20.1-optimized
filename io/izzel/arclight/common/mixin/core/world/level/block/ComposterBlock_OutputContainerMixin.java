/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.ComposterBlock
 *  net.minecraft.world.level.block.ComposterBlock$OutputContainer
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.mixin.core.world.SimpleContainerMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftBlockInventoryHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ComposterBlock.OutputContainer.class})
public abstract class ComposterBlock_OutputContainerMixin
extends SimpleContainerMixin {
    @Shadow
    @Final
    private BlockState f_52037_;
    @Shadow
    @Final
    private LevelAccessor f_52038_;
    @Shadow
    @Final
    private BlockPos f_52039_;
    @Shadow
    private boolean f_52040_;

    @Inject(method={"<init>(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V"}, at={@At(value="RETURN")})
    public void arclight$setOwner(BlockState blockState, LevelAccessor world, BlockPos blockPos, ItemStack itemStack, CallbackInfo ci) {
        this.setOwner(new CraftBlockInventoryHolder(world, blockPos, this));
    }

    @Overwrite
    public void m_6596_() {
        if (this.m_7983_()) {
            ComposterBlock.m_269590_(null, (BlockState)this.f_52037_, (LevelAccessor)this.f_52038_, (BlockPos)this.f_52039_);
            this.f_52040_ = true;
        } else {
            this.f_52038_.m_7731_(this.f_52039_, this.f_52037_, 3);
            this.f_52040_ = false;
        }
    }
}

