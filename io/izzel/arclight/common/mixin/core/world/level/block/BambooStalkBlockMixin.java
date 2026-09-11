/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.BambooStalkBlock
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BambooLeaves
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.mixin.core.world.level.block.BlockMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={BambooStalkBlock.class})
public abstract class BambooStalkBlockMixin
extends BlockMixin {
    @Shadow
    @Final
    public static EnumProperty<BambooLeaves> f_260716_;
    @Shadow
    @Final
    public static IntegerProperty f_260603_;
    @Shadow
    @Final
    public static IntegerProperty f_260694_;

    @Redirect(method={"performBonemeal"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;"))
    private <T extends Comparable<T>> T arclight$skipIfCancel(BlockState state, Property<T> property) {
        if (!state.m_60713_(Blocks.f_50571_)) {
            return (T)Integer.valueOf(1);
        }
        return (T)state.m_61143_(property);
    }

    @Overwrite
    protected void m_261305_(BlockState blockStateIn, Level worldIn, BlockPos posIn, RandomSource rand, int height) {
        int newState;
        BlockState blockstate = worldIn.m_8055_(posIn.m_7495_());
        BlockPos blockpos = posIn.m_6625_(2);
        BlockState blockstate1 = worldIn.m_8055_(blockpos);
        BambooLeaves bambooleaves = BambooLeaves.NONE;
        boolean update = false;
        if (height >= 1) {
            if (blockstate.m_60713_(Blocks.f_50571_) && blockstate.m_61143_(f_260716_) != BambooLeaves.NONE) {
                if (blockstate.m_60713_(Blocks.f_50571_) && blockstate.m_61143_(f_260716_) != BambooLeaves.NONE) {
                    bambooleaves = BambooLeaves.LARGE;
                    if (blockstate1.m_60713_(Blocks.f_50571_)) {
                        update = true;
                    }
                }
            } else {
                bambooleaves = BambooLeaves.SMALL;
            }
        }
        int newAge = (Integer)blockStateIn.m_61143_((Property)f_260603_) != 1 && !blockstate1.m_60713_(Blocks.f_50571_) ? 0 : 1;
        int n = newState = !(height >= 11 && rand.m_188501_() < 0.25f || height == 15) ? 0 : 1;
        if (CraftEventFactory.handleBlockSpreadEvent((LevelAccessor)worldIn, posIn, posIn.m_7494_(), (BlockState)((BlockState)((BlockState)this.m_49966_().m_61124_((Property)f_260603_, (Comparable)Integer.valueOf(newAge))).m_61124_(f_260716_, (Comparable)bambooleaves)).m_61124_((Property)f_260694_, (Comparable)Integer.valueOf(newState)), 3) && update) {
            worldIn.m_7731_(posIn.m_7495_(), (BlockState)blockstate.m_61124_(f_260716_, (Comparable)BambooLeaves.SMALL), 3);
            worldIn.m_7731_(blockpos, (BlockState)blockstate1.m_61124_(f_260716_, (Comparable)BambooLeaves.NONE), 3);
        }
    }
}

