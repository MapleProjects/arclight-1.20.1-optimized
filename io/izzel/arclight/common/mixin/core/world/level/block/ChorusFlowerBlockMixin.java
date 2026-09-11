/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Plane
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.ChorusFlowerBlock
 *  net.minecraft.world.level.block.ChorusPlantBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraftforge.common.ForgeHooks
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.mixin.core.world.level.block.BlockMixin;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeHooks;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChorusFlowerBlock.class})
public abstract class ChorusFlowerBlockMixin
extends BlockMixin {
    @Shadow
    @Final
    public static IntegerProperty f_51647_;
    @Shadow
    @Final
    private ChorusPlantBlock f_51648_;

    @Shadow
    private static boolean m_51697_(LevelReader worldIn, BlockPos pos, @Nullable Direction excludingSide) {
        return false;
    }

    @Shadow
    protected abstract void m_51661_(Level var1, BlockPos var2, int var3);

    @Shadow
    protected abstract void m_51658_(Level var1, BlockPos var2);

    @Inject(method={"randomTick"}, at={@At(value="HEAD")}, cancellable=true)
    public void arclight$randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random, CallbackInfo ci) {
        this.arclight$performRandomTick(state, worldIn, pos, random);
        ci.cancel();
    }

    private void arclight$performRandomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        int i;
        BlockPos blockpos = pos.m_7494_();
        if (worldIn.m_46859_(blockpos) && blockpos.m_123342_() < 256 && (i = ((Integer)state.m_61143_((Property)f_51647_)).intValue()) < 5 && ForgeHooks.onCropsGrowPre((Level)worldIn, (BlockPos)blockpos, (BlockState)state, (boolean)true)) {
            boolean flag = false;
            boolean flag1 = false;
            BlockState blockstate = worldIn.m_8055_(pos.m_7495_());
            Block block = blockstate.m_60734_();
            if (block == Blocks.f_50259_) {
                flag = true;
            } else if (block == this.f_51648_) {
                int j = 1;
                for (int k = 0; k < 4; ++k) {
                    Block block1 = worldIn.m_8055_(pos.m_6625_(j + 1)).m_60734_();
                    if (block1 != this.f_51648_) {
                        if (block1 != Blocks.f_50259_) break;
                        flag1 = true;
                        break;
                    }
                    ++j;
                }
                if (j < 2 || j <= random.m_188503_(flag1 ? 5 : 4)) {
                    flag = true;
                }
            } else if (blockstate.m_60795_()) {
                flag = true;
            }
            if (flag && ChorusFlowerBlockMixin.m_51697_((LevelReader)worldIn, blockpos, null) && worldIn.m_46859_(pos.m_6630_(2))) {
                if (CraftEventFactory.handleBlockSpreadEvent((LevelAccessor)worldIn, pos, blockpos, (BlockState)this.m_49966_().m_61124_((Property)ChorusFlowerBlock.f_51647_, (Comparable)Integer.valueOf(i)), 2)) {
                    worldIn.m_7731_(pos, this.f_51648_.m_51710_((BlockGetter)worldIn, pos), 2);
                    this.m_51661_((Level)worldIn, blockpos, i);
                }
            } else if (i < 4) {
                int l = random.m_188503_(4);
                if (flag1) {
                    ++l;
                }
                boolean flag2 = false;
                for (int i1 = 0; i1 < l; ++i1) {
                    Direction direction = Direction.Plane.HORIZONTAL.m_235690_(random);
                    BlockPos blockpos1 = pos.m_121945_(direction);
                    if (!worldIn.m_46859_(blockpos1) || !worldIn.m_46859_(blockpos1.m_7495_()) || !ChorusFlowerBlockMixin.m_51697_((LevelReader)worldIn, blockpos1, direction.m_122424_()) || !CraftEventFactory.handleBlockSpreadEvent((LevelAccessor)worldIn, pos, blockpos1, (BlockState)this.m_49966_().m_61124_((Property)ChorusFlowerBlock.f_51647_, (Comparable)Integer.valueOf(i + 1)), 2)) continue;
                    this.m_51661_((Level)worldIn, blockpos1, i + 1);
                    flag2 = true;
                }
                if (flag2) {
                    worldIn.m_7731_(pos, this.f_51648_.m_51710_((BlockGetter)worldIn, pos), 2);
                } else if (CraftEventFactory.handleBlockGrowEvent((Level)worldIn, pos, (BlockState)this.m_49966_().m_61124_((Property)ChorusFlowerBlock.f_51647_, (Comparable)Integer.valueOf(5)), 2)) {
                    this.m_51658_((Level)worldIn, pos);
                }
            } else if (CraftEventFactory.handleBlockGrowEvent((Level)worldIn, pos, (BlockState)this.m_49966_().m_61124_((Property)ChorusFlowerBlock.f_51647_, (Comparable)Integer.valueOf(5)), 2)) {
                this.m_51658_((Level)worldIn, pos);
            }
            ForgeHooks.onCropsGrowPost((Level)worldIn, (BlockPos)pos, (BlockState)state);
        }
    }

    @Inject(method={"onProjectileHit"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;destroyBlock(Lnet/minecraft/core/BlockPos;ZLnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$hitByProjectile(Level p_51654_, BlockState p_51655_, BlockHitResult result, Projectile projectile, CallbackInfo ci) {
        if (!CraftEventFactory.callEntityChangeBlockEvent((Entity)projectile, result.m_82425_(), Blocks.f_50016_.m_49966_())) {
            ci.cancel();
        }
    }
}

