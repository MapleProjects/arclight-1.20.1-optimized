/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.ChestBlock
 *  net.minecraft.world.level.block.DoubleBlockCombiner$Combiner
 *  net.minecraft.world.level.block.DoubleBlockCombiner$NeighborCombineResult
 *  net.minecraft.world.level.block.entity.ChestBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ChestBlock.class})
public abstract class ChestBlockMixin {
    @Shadow
    @Final
    private static DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<MenuProvider>> f_51487_;

    @Shadow
    public abstract DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> m_5641_(BlockState var1, Level var2, BlockPos var3, boolean var4);

    @Nullable
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos, boolean ignoreObstructions) {
        return ((Optional)this.m_5641_(state, level, pos, ignoreObstructions).m_5649_(f_51487_)).orElse(null);
    }
}

