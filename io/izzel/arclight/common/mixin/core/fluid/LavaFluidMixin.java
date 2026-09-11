/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.material.LavaFluid
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.fluid;

import io.izzel.arclight.common.bridge.core.world.IWorldBridge;
import io.izzel.arclight.common.mod.util.DistValidate;
import io.izzel.arclight.mixin.Eject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LavaFluid.class})
public abstract class LavaFluidMixin {
    @Shadow
    protected abstract boolean m_76227_(LevelReader var1, BlockPos var2);

    @Shadow(remap=false)
    protected abstract boolean isFlammable(LevelReader var1, BlockPos var2, Direction var3);

    @Overwrite
    public void m_213812_(Level world, BlockPos pos, FluidState state, RandomSource random) {
        block7: {
            if (!world.m_46469_().m_46207_(GameRules.f_46131_)) break block7;
            int i = random.m_188503_(3);
            if (i > 0) {
                BlockPos blockpos = pos;
                for (int j = 0; j < i; ++j) {
                    if (!world.m_46749_(blockpos = blockpos.m_7918_(random.m_188503_(3) - 1, 1, random.m_188503_(3) - 1))) {
                        return;
                    }
                    BlockState blockstate = world.m_8055_(blockpos);
                    if (blockstate.m_60795_()) {
                        if (!this.m_76227_((LevelReader)world, blockpos) || world.m_8055_(blockpos).m_60734_() != Blocks.f_50083_ && DistValidate.isValid((LevelAccessor)world) && CraftEventFactory.callBlockIgniteEvent(world, blockpos, pos).isCancelled()) continue;
                        world.m_46597_(blockpos, ForgeEventFactory.fireFluidPlaceBlockEvent((LevelAccessor)world, (BlockPos)blockpos, (BlockPos)pos, (BlockState)Blocks.f_50083_.m_49966_()));
                        return;
                    }
                    if (!blockstate.m_280555_()) continue;
                    return;
                }
            } else {
                for (int k = 0; k < 3; ++k) {
                    BlockPos up;
                    BlockPos blockpos1 = pos.m_7918_(random.m_188503_(3) - 1, 0, random.m_188503_(3) - 1);
                    if (!world.m_46749_(blockpos1)) {
                        return;
                    }
                    if (!world.m_46859_(blockpos1.m_7494_()) || !this.isFlammable((LevelReader)world, blockpos1, Direction.UP) || world.m_8055_(up = blockpos1.m_7494_()).m_60734_() != Blocks.f_50083_ && DistValidate.isValid((LevelAccessor)world) && CraftEventFactory.callBlockIgniteEvent(world, up, pos).isCancelled()) continue;
                    world.m_46597_(blockpos1.m_7494_(), ForgeEventFactory.fireFluidPlaceBlockEvent((LevelAccessor)world, (BlockPos)blockpos1.m_7494_(), (BlockPos)pos, (BlockState)Blocks.f_50083_.m_49966_()));
                }
            }
        }
    }

    @Eject(method={"spreadTo"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/LevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean arclight$blockFromTo(LevelAccessor world, BlockPos pos, BlockState newState, int flags, CallbackInfo ci) {
        if (!DistValidate.isValid(world)) {
            return world.m_7731_(pos, newState, flags);
        }
        if (!CraftEventFactory.handleBlockFormEvent((Level)((IWorldBridge)world).bridge$getMinecraftWorld(), pos, newState, flags)) {
            ci.cancel();
            return false;
        }
        return true;
    }
}

