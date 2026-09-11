/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Plane
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.VineBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.BlockMixin;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={VineBlock.class})
public abstract class VineBlockMixin
extends BlockMixin {
    @Shadow
    @Final
    public static BooleanProperty f_57833_;

    @Shadow
    public static BooleanProperty m_57883_(Direction side) {
        return null;
    }

    @Shadow
    protected abstract boolean m_57850_(BlockGetter var1, BlockPos var2);

    @Shadow
    public static boolean m_57853_(BlockGetter blockReader, BlockPos worldIn, Direction neighborPos) {
        return false;
    }

    @Shadow
    protected abstract boolean m_57887_(BlockGetter var1, BlockPos var2, Direction var3);

    @Shadow
    protected abstract boolean m_57911_(BlockState var1);

    @Shadow
    protected abstract BlockState m_222650_(BlockState var1, BlockState var2, RandomSource var3);

    @Overwrite
    public void m_213898_(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        if (!worldIn.m_46469_().m_46207_(GameRules.f_268705_)) {
            return;
        }
        if (worldIn.f_46441_.m_188503_(4) == 0 && worldIn.isAreaLoaded(pos, 4)) {
            Direction direction = Direction.m_235672_((RandomSource)random);
            BlockPos blockpos = pos.m_7494_();
            if (direction.m_122434_().m_122479_() && !((Boolean)state.m_61143_((Property)VineBlockMixin.m_57883_(direction))).booleanValue()) {
                if (this.m_57850_((BlockGetter)worldIn, pos)) {
                    BlockState newState;
                    BlockPos blockpos4 = pos.m_121945_(direction);
                    BlockState blockstate4 = worldIn.m_8055_(blockpos4);
                    if (blockstate4.m_60795_()) {
                        Direction direction3 = direction.m_122427_();
                        Direction direction4 = direction.m_122428_();
                        boolean flag = (Boolean)state.m_61143_((Property)VineBlockMixin.m_57883_(direction3));
                        boolean flag1 = (Boolean)state.m_61143_((Property)VineBlockMixin.m_57883_(direction4));
                        BlockPos blockpos2 = blockpos4.m_121945_(direction3);
                        BlockPos blockpos3 = blockpos4.m_121945_(direction4);
                        if (flag && VineBlockMixin.m_57853_((BlockGetter)worldIn, blockpos2, direction3)) {
                            BlockState newState2 = (BlockState)this.m_49966_().m_61124_((Property)VineBlockMixin.m_57883_(direction3), (Comparable)Boolean.TRUE);
                            if (this.arclight$blockSpread(worldIn, pos, blockpos4, newState2, 2)) {
                                worldIn.m_7731_(blockpos4, newState2, 2);
                            }
                        } else if (flag1 && VineBlockMixin.m_57853_((BlockGetter)worldIn, blockpos3, direction4)) {
                            BlockState newState3 = (BlockState)this.m_49966_().m_61124_((Property)VineBlockMixin.m_57883_(direction4), (Comparable)Boolean.TRUE);
                            if (this.arclight$blockSpread(worldIn, pos, blockpos4, newState3, 2)) {
                                worldIn.m_7731_(blockpos4, newState3, 2);
                            }
                        } else {
                            BlockState newState4;
                            Direction direction1 = direction.m_122424_();
                            if (flag && worldIn.m_46859_(blockpos2) && VineBlockMixin.m_57853_((BlockGetter)worldIn, pos.m_121945_(direction3), direction1)) {
                                BlockState newState5 = (BlockState)this.m_49966_().m_61124_((Property)VineBlockMixin.m_57883_(direction1), (Comparable)Boolean.TRUE);
                                if (this.arclight$blockSpread(worldIn, pos, blockpos2, newState5, 2)) {
                                    worldIn.m_7731_(blockpos2, newState5, 2);
                                }
                            } else if (flag1 && worldIn.m_46859_(blockpos3) && VineBlockMixin.m_57853_((BlockGetter)worldIn, pos.m_121945_(direction4), direction1)) {
                                BlockState newState6 = (BlockState)this.m_49966_().m_61124_((Property)VineBlockMixin.m_57883_(direction1), (Comparable)Boolean.TRUE);
                                if (this.arclight$blockSpread(worldIn, pos, blockpos3, newState6, 2)) {
                                    worldIn.m_7731_(blockpos3, newState6, 2);
                                }
                            } else if ((double)worldIn.f_46441_.m_188501_() < 0.05 && VineBlockMixin.m_57853_((BlockGetter)worldIn, blockpos4.m_7494_(), Direction.UP) && this.arclight$blockSpread(worldIn, pos, blockpos4, newState4 = (BlockState)this.m_49966_().m_61124_((Property)f_57833_, (Comparable)Boolean.TRUE), 2)) {
                                worldIn.m_7731_(blockpos4, newState4, 2);
                            }
                        }
                    } else if (VineBlockMixin.m_57853_((BlockGetter)worldIn, blockpos4, direction) && this.arclight$blockGrow(worldIn, pos, newState = (BlockState)state.m_61124_((Property)VineBlockMixin.m_57883_(direction), (Comparable)Boolean.TRUE), 2)) {
                        worldIn.m_7731_(pos, newState, 2);
                    }
                }
            } else {
                BlockState blockstate2;
                BlockState blockstate1;
                BlockPos blockpos1;
                BlockState blockstate;
                boolean isAir;
                if (direction == Direction.UP && pos.m_123342_() < worldIn.m_151558_() - 1) {
                    if (this.m_57887_((BlockGetter)worldIn, pos, direction)) {
                        BlockState newState = (BlockState)state.m_61124_((Property)f_57833_, (Comparable)Boolean.TRUE);
                        if (this.arclight$blockGrow(worldIn, pos, newState, 2)) {
                            worldIn.m_7731_(pos, newState, 2);
                        }
                        return;
                    }
                    if (worldIn.m_46859_(blockpos)) {
                        if (!this.m_57850_((BlockGetter)worldIn, pos)) {
                            return;
                        }
                        BlockState blockstate3 = state;
                        for (Direction direction2 : Direction.Plane.HORIZONTAL) {
                            if (!random.m_188499_() && VineBlockMixin.m_57853_((BlockGetter)worldIn, blockpos.m_121945_(direction2), Direction.UP)) continue;
                            blockstate3 = (BlockState)blockstate3.m_61124_((Property)VineBlockMixin.m_57883_(direction2), (Comparable)Boolean.FALSE);
                        }
                        if (this.m_57911_(blockstate3) && this.arclight$blockSpread(worldIn, pos, blockpos, blockstate3, 2)) {
                            worldIn.m_7731_(blockpos, blockstate3, 2);
                        }
                        return;
                    }
                }
                if (pos.m_123342_() > worldIn.m_141937_() && ((isAir = (blockstate = worldIn.m_8055_(blockpos1 = pos.m_7495_())).m_60795_()) || blockstate.m_60713_((Block)this)) && (blockstate1 = isAir ? this.m_49966_() : blockstate) != (blockstate2 = this.m_222650_(state, blockstate1, random)) && this.m_57911_(blockstate2) && this.arclight$blockSpread(worldIn, pos, blockpos1, blockstate2, 2)) {
                    worldIn.m_7731_(blockpos1, blockstate2, 2);
                }
            }
        }
    }

    private boolean arclight$blockSpread(ServerLevel world, BlockPos source, BlockPos target, BlockState block, int flag) {
        if (!DistValidate.isValid((LevelAccessor)world)) {
            return true;
        }
        CraftBlockState state = CraftBlockStates.getBlockState((LevelAccessor)world, target, flag);
        state.setData(block);
        BlockSpreadEvent event = new BlockSpreadEvent(state.getBlock(), CraftBlock.at((LevelAccessor)world, source), state);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }

    private boolean arclight$blockGrow(ServerLevel world, BlockPos pos, BlockState newData, int flag) {
        if (!DistValidate.isValid((LevelAccessor)world)) {
            return true;
        }
        org.bukkit.block.Block block = ((WorldBridge)world).bridge$getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_());
        CraftBlockState state = (CraftBlockState)block.getState();
        state.setData(newData);
        BlockGrowEvent event = new BlockGrowEvent(block, state);
        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled();
    }
}

