/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.FungusBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.mod.util.ArclightCaptures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.TreeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={FungusBlock.class})
public class FungusBlockMixin {
    @Inject(method={"performBonemeal"}, at={@At(value="HEAD")})
    private void arclight$captureTree(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (this == Blocks.f_50691_) {
            ArclightCaptures.captureTreeType(TreeType.WARPED_FUNGUS);
        } else if (this == Blocks.f_50700_) {
            ArclightCaptures.captureTreeType(TreeType.CRIMSON_FUNGUS);
        }
    }
}

